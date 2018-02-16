package com.SDP.BLL;

import com.SDP.Models.*;
import com.SDP.Repositories.*;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
@Component
public class CourseRecommendation {

    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private EmployeeCoursesRepository employeeCoursesRepository;
    @Autowired
    private FunctionsDomainsRepository functionsDomainsRepository;
    @Autowired
    private FunctionsRepository functionsRepository;
    @Autowired
    private DomainsRepository domainsRepository;

    private Courses recommended_course;
    private Employees selectedEmployee;
    private Functions employeesFunction;
    private List<FunctionsDomains> listFunctionDomains;
    private List<Domains> domainsList;





    public Employees searchCourseForEmployeeId(int id){
        selectedEmployee = employeesRepository.findById(id);

        return selectedEmployee;
    }


}
