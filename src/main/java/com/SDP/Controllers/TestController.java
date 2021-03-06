package com.SDP.Controllers;

import com.SDP.BLL.CourseRecommendation;
import com.SDP.BLL.TimeTracking;
import com.SDP.BLL.TopSkills;
import com.SDP.Models.Courses;
import com.SDP.Models.Employees;
import com.SDP.Models.ScoresObject;
import com.SDP.Models.TimeTrackingObject;
import com.SDP.Repositories.EmployeesRepository;
import com.SDP.Repositories.FunctionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping(path="/test")
public class TestController {

    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private TopSkills ts;
    @Autowired
    private CourseRecommendation cr;
    @Autowired
    private FunctionsRepository fr;
    @Autowired
    private ExperienceController ec;
    @Autowired
    private TimeTracking tt;

    Employees emp;


    @RequestMapping(value = "/check/1", method = RequestMethod.GET)
    public @ResponseBody
    Employees testMeth1() {
        emp = employeesRepository.findById(1);
        emp.setSex("F");
        employeesRepository.save(emp);
        return emp;
    }

    @RequestMapping(value = "/check/2", method = RequestMethod.GET)
    public ScoresObject testMeth2() {
        return ts.getTop5ForEmployeeId(1);
    }

    @RequestMapping(value = "/check/3", method = RequestMethod.GET)
    public @ResponseBody
    List<Courses> testMeth3() {
        return cr.RecommendByPriorityByEmployeeId(1, 2);
    }

    @RequestMapping(value = "/check/4", method = RequestMethod.GET)
    public @ResponseBody
    TimeTrackingObject testMeth4() {
        return tt.getAllSetsForEmployeeWithFunction(1,1);

    }
}