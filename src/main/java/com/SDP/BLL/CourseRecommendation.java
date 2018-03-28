package com.SDP.BLL;

import com.SDP.Models.*;
import com.SDP.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseRecommendation {

    @Autowired
    EmployeesRepository er;
    @Autowired
    EmployeesFunctionsRepository efr;
    @Autowired
    FunctionsDomainsRepository fdr;
    @Autowired
    CoursesRepository cr;
    @Autowired
    EmployeeCoursesRepository ecr;

    public List<Courses> RecommendByPriorityByEmployeeId(int id){

        boolean courseFound = false;
        int functionCounter = 0;
        int domainCounter = 0;

        Employees selectedEmployee = er.findById(id);
        List<EmployeesFunctions> employeesFunctions = efr.findAllByEmployee_Id(id);
        EmployeesFunctions selectedEmployeesFunction;
        List<FunctionsDomains> functionsDomainsList = null;
        List<Courses> boundCourses;
        List<Courses> followedCourses = new ArrayList<Courses>();
        List<EmployeeCourses> employeeCoursesList;
        List<Courses> filteredCourses = new ArrayList<Courses>();


        do{
            //Get function in functionlist
            if(employeesFunctions.size() > functionCounter){
                //select the first function in the list
                selectedEmployeesFunction = employeesFunctions.get(functionCounter);
                functionCounter++;
                //Get functiondomains object;
                 functionsDomainsList=  fdr.findAllByFunction_IdOrderByDomainPriorityDesc(selectedEmployeesFunction.getFunction().getId());
            }
            else{
                break;
            }
                //select the first domain in the list
                Domains selectedDomain = functionsDomainsList.get(domainCounter).getDomain();
                //find courses connected to domain
                boundCourses = cr.findAllByDomain_Id(selectedDomain.getId());
                //get followed courses
                employeeCoursesList = ecr.findAllByEmployee_Id(id);
                //Extract the courses from the EmployeeCourses Objects
                for(EmployeeCourses ec : employeeCoursesList){
                    followedCourses.add(ec.getCourse());
                }

                //Check if courses are followed by comparing ID's
                for(Courses bc : boundCourses){
                    if(!followedCourses.contains(bc)){
                        filteredCourses.add(bc);
                    }
                }
                //check if a course is found
                if(filteredCourses.size() > 0){
                    courseFound = true;
                }

        }while(!courseFound);
        return filteredCourses;
    }
}