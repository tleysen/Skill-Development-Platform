package com.SDP.BLL;

import com.SDP.Models.Courses;
import com.SDP.Models.EmployeeCourses;
import com.SDP.Models.Employees;
import com.SDP.Models.FunctionsDomains;
import com.SDP.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExperienceCalculation {


    @Autowired
    private EmployeesRepository er;
    @Autowired
    private ScoresRepository sr;
    @Autowired
    private EmployeeCoursesRepository ecr;
    @Autowired
    private FunctionsDomainsRepository fdr;
    @Autowired
    private CoursesRepository cr;


    public int calculateTotalExperiencepoints(int employee_id, String func_name){

        int exp = 0;
        List<FunctionsDomains> boundDomains;
        List<Courses> boundCourses = new ArrayList<>();
        List<Courses> wa_courses;
        List<Courses> followedCourses = new ArrayList<>();
        List<EmployeeCourses> wa_emplcourses;

        //All bound domains for given function
        boundDomains = fdr.findAllByFunction_Name(func_name);
        //find all courses for bound domains
        for(FunctionsDomains fd: boundDomains) {
            //list of courses bound to fd domain
            wa_courses = cr.findAllByDomain_Id(fd.getDomain().getId());
            boundCourses.addAll(wa_courses);
        }
        //All employee courses objects
        wa_emplcourses = ecr.findAllByEmployee_Id(employee_id);
        //convert list to courses list
        for (EmployeeCourses ec: wa_emplcourses){
            followedCourses.add(ec.getCourse());
        }
        //compare 2 lists
        for(Courses c:boundCourses){
            if(followedCourses.contains(c)){
                //add matching course's exp points to total
                exp += c.getExp();
            }
        }
        return exp;
    }

}
