package com.SDP.BLL;

import com.SDP.Models.*;
import com.SDP.Repositories.*;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CourseRecommendation {

    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private EmployeeCoursesRepository employeeCoursesRepository;
    @Autowired
    private FunctionsDomainsRepository functionsDomainsRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private DomainsRepository domainsRepository;




    private Courses recommendedCourse;
    private Employees selectedEmployee;
    private Functions employeesFunction;
    private List<FunctionsDomains> listDomainsInFunction = new ArrayList<FunctionsDomains>();
    private List<Domains> domainsList = new ArrayList<Domains>();
    private List<String> domainsIdList = new ArrayList<String>();
    private List<Courses> coursesList = new ArrayList<Courses>();
    private List<EmployeeCourses> employeeCoursesList = new ArrayList<EmployeeCourses>();
    private List<Courses> followedCoursesList = new ArrayList<Courses>();

    private String domainid;
    private int functionid;
    private int counter = 0;

    public List<Domains> GetDomainsForEmployeeId(int id){

        domainsIdList.clear();
        domainsList.clear();

        selectedEmployee = employeesRepository.findById(id);
        employeesFunction = selectedEmployee.getFunction();
        functionid = employeesFunction.getId();
        listDomainsInFunction = functionsDomainsRepository.findAllByFunction_Id(functionid);

        for (FunctionsDomains functiondomain : listDomainsInFunction) {
            domainid = functiondomain.getDomain().getId().toString();
            domainsIdList.add(counter, domainid);
            counter++;
        }

        counter = 0;

        for(String domainid : domainsIdList) {
            domainsList.add(counter, domainsRepository.findById(Integer.parseInt(domainid)));
            counter++;
        }

        counter = 0;
        return domainsList;
    }

    public List<Courses> FollowedCoursesEmployees(int id){

        coursesList = coursesRepository.findAllByDomain_Id(id);
        employeeCoursesList = employeeCoursesRepository.findAllByEmployee_Id(1);

        for (EmployeeCourses employeeCourses : employeeCoursesList) {

            followedCoursesList.add(employeeCourses.getCourse());
            counter++;
        }

        counter = 0;
        return followedCoursesList;
    }


}
