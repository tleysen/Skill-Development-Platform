package com.SDP.Controllers;

import com.SDP.BLL.CourseRecommendation;
import com.SDP.Models.*;
import com.SDP.Repositories.*;
import org.hibernate.context.spi.CurrentSessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path="/req") // This means URL's start with /XXX (after Application path)
public class MainController {

    //------------------------------------------------------------------------------------------------------------------
    //*****                                     INSTANCES                                                          *****
    //------------------------------------------------------------------------------------------------------------------


    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private DomainsRepository domainsRepository;
    @Autowired
    private ScoresRepository scoreRepository;
    @Autowired
    private FunctionsDomainsRepository functionsDomainsRepository;
    @Autowired
    private FunctionsRepository functionsRepository;
    @Autowired
    private CourseRecommendation courseRecommendation;

    //------------------------------------------------------------------------------------------------------------------
    //*****                                     VARIABLES                                                          *****
    //------------------------------------------------------------------------------------------------------------------

    private Employees selectedEmployee;
    private Functions employeesFunction;



    //------------------------------------------------------------------------------------------------------------------
    //*****                                     PARAMLESS GET'S                                                    *****
    //------------------------------------------------------------------------------------------------------------------

    @GetMapping(path = "/allusers")
    public @ResponseBody
    Iterable<Employees> getAllUsers() {
        // This returns a JSON or XML with the users
        return employeesRepository.findAll();
    }

    @GetMapping(path = "/alldomains")
    public @ResponseBody
    Iterable<Domains> getAllDomains() {
        return domainsRepository.findAll();
    }

    @GetMapping(path = "/allscores")
    public @ResponseBody
    Iterable<Scores> getAllScores() {
        return scoreRepository.findAll();
    }

    //------------------------------------------------------------------------------------------------------------------
    //*****                                     PARAMETER GET'S                                                    *****
    //------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/userbyid/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Employees getUserById(
            @PathVariable("id") String id) {
        return employeesRepository.findById(Integer.parseInt(id));
    }

    @RequestMapping(value = "/functionbyid/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Functions getFunctionById(
            @PathVariable("id") String id) {
        return functionsRepository.findById(Integer.parseInt(id));
    }

    @RequestMapping(value = "/domainbyid/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Domains getDomainById(
            @PathVariable("id") String id) {
        return domainsRepository.findById(Integer.parseInt(id));
    }

    @GetMapping(path = "/domainbyemployeeid/{id}")
    public @ResponseBody
    List<Domains> getDomainsForEmployeeId(@PathVariable("id") String id) {
        return courseRecommendation.GetDomainsForEmployeeId(Integer.parseInt(id));
    }

    @GetMapping(path = "/coursesbydomainid/{id}")
    public @ResponseBody
    List<Courses> getCoursesForDomain(@PathVariable("id") String id) {
        return courseRecommendation.FollowedCoursesEmployees(Integer.parseInt(id));
    }

    @GetMapping(path = "/recommendCourse/{id}")
    public @ResponseBody
    String getCoursesForEmp(@PathVariable("id") String id) {
        return courseRecommendation.RecommendCourseByPriority(Integer.parseInt(id));
    }


    //------------------------------------------------------------------------------------------------------------------
    //****                                     PARAMETER POST'S                                                    *****
    //------------------------------------------------------------------------------------------------------------------

    @GetMapping(path = "/addEmployee")
    public @ResponseBody
    String addNewEmployee(@RequestParam String name
            , @RequestParam String lastname) {

        selectedEmployee = employeesRepository.findById(1);
        employeesFunction = selectedEmployee.getFunction();

        Employees n = new Employees();
        n.setName(name);
        n.setLastname(lastname);
        n.setFunction(employeesFunction);
        employeesRepository.save(n);
        return "Saved";
    }


}