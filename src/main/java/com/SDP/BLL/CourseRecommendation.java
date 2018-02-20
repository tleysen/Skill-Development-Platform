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
import java.util.Random;

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


    private List<FunctionsDomains> listDomainsInFunction = new ArrayList<FunctionsDomains>();
    private List<Domains> domainsList = new ArrayList<Domains>();
    private List<String> domainsIdList = new ArrayList<String>();
    private List<Courses> coursesList = new ArrayList<Courses>();
    private List<EmployeeCourses> employeeCoursesList = new ArrayList<EmployeeCourses>();
    private List<Courses> followedCoursesList = new ArrayList<Courses>();
    private List<Courses> coursesForDomainList = new ArrayList<Courses>();
    private List<Employees> allEmployeesList = new ArrayList<Employees>();
    private List<EmployeeCourses> allCoursesList = new ArrayList<EmployeeCourses>();
    private Random rand = new Random();

    private String domainid;
    private int functionid;
    private int counter;
    private Courses recommendedCourse;
    private Employees selectedEmployee;
    private Functions employeesFunction;
    private Domains selectedDomain;
    private Employees checkEmployee;
    private Courses checkCourse;
    private Iterable<Employees> allEmployeesIterable;
    private Iterable<EmployeeCourses> allCoursesIterable;

    private boolean


    public void Init(){

        listDomainsInFunction.clear();
        domainsIdList.clear();
        domainsList.clear();
        coursesList.clear();
        employeeCoursesList.clear();
        followedCoursesList.clear();
        counter = 0;

    }

    public List<Domains> CoupledDomainsByFunctionid(int id){

        Init();

        listDomainsInFunction = functionsDomainsRepository.findAllByFunction_Id(id);

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


    public boolean checkIfCourseFollowed(int employee_id, int course_id) {

        allCoursesIterable = employeeCoursesRepository.findAll();
        allCoursesIterable.forEach(allCoursesList::add);

        for (EmployeeCourses course : allCoursesList) {
            if (course.getCourse().getId() == course_id && course.getEmployee().getId() == employee_id) {
                return true;
                break;
            } else {
                return false;
                break;
            }
        }
    }

    public String RecommendCourseByPriority(int employee_id){

        Init();

        domainsList = GetDomainsForEmployeeId(employee_id);



        for(Domains domain : domainsList) {
            if(selectedDomain == null || domain.getPriority() > selectedDomain.getPriority()){
                selectedDomain = domain;
            }

        }

        coursesForDomainList = GetCoursesForDomain(selectedDomain.getId());

        if(coursesForDomainList.size() > 1) {
            int rnd = rand.nextInt(coursesForDomainList.size());
            recommendedCourse = coursesForDomainList.get(rnd);
        }
        else if(coursesForDomainList.size() == 1) {

            recommendedCourse = coursesForDomainList.get(0);
        }
        else {
            return "No courses found";
        }


        return recommendedCourse.toString();

    }

    public List<Courses> GetCoursesForDomain(int id){

        Init();

        coursesList = coursesRepository.findAllByDomain_Id(id);

        return coursesList;
    }

    public List<Domains> GetDomainsForEmployeeId(int id){

        Init();

        selectedEmployee = employeesRepository.findById(id);
        employeesFunction = selectedEmployee.getFunction();
        functionid = employeesFunction.getId();
        return CoupledDomainsByFunctionid(functionid);
    }

    public List<Courses> FollowedCoursesEmployees(int id){

        Init();

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
