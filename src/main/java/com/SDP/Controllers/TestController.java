package com.SDP.Controllers;

import com.SDP.BLL.CourseRecommendation;
import com.SDP.Models.Employees;
import com.SDP.Repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/test")
public class TestController {

    @Autowired
    private CourseRecommendation courseRecommendation;
    @Autowired
    private EmployeesRepository employeesRepository;

    Employees emp;


    @RequestMapping(value = "/check/1", method = RequestMethod.GET)
    public @ResponseBody
    Employees testMeth1() {
        emp = employeesRepository.findById(1);

        emp.setSex("F");
        employeesRepository.save(emp);

        return emp;
    };

    @RequestMapping(value = "/check/2", method = RequestMethod.GET)
    public @ResponseBody
    Boolean testMeth2() {
        return courseRecommendation.checkIfCourseFollowed(1,4);
    }
}