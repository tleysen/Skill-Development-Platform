package com.SDP.Controllers;

import com.SDP.BLL.CourseRecommendation;
import com.SDP.Models.*;
import com.SDP.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(path="/req") // This means URL's start with /XXX (after Application path)
public class MainController {
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private DomainsRepository domainRepository;
    @Autowired
    private ScoresRepository scoreRepository;
    @Autowired
    private FunctionsDomainsRepository functionsDomainsRepository;
    @Autowired
    private FunctionsRepository functionsRepository;
    private CourseRecommendation courseRecommendation;
    private Courses recommended_course;
    private Employees selectedEmployee;
    private Functions employeesFunction;
    private List<FunctionsDomains> listDomainsInFunction;
    private List<Domains> domainsList;
    private int domainid;


    //@GetMapping(path="/add") // Map ONLY GET Requests
    //public @ResponseBody String addNewUser (@RequestParam String name
    //		, @RequestParam String email) {
    //	// @ResponseBody means the returned String is the response, not a view name
    //	// @RequestParam means it is a parameter from the GET or POST request
//
    //	Employees n = new Employees();
    //	n.setName(name);
    //	n.setLastname(email);
    //	userRepository.save(n);
    //	return "Saved";
    //}

    @GetMapping(path = "/allusers")
    public @ResponseBody
    Iterable<Employees> getAllUsers() {
        // This returns a JSON or XML with the users
        return employeesRepository.findAll();
    }


    @GetMapping(path = "/testurl")
    public @ResponseBody
    List<FunctionsDomains> getFDS(){
        selectedEmployee = employeesRepository.findById(1);
        employeesFunction = selectedEmployee.getFunction();
        domainid = employeesFunction.getId();

        //ERROR, Table 'skill_development_db.functions_domains' doesn't exist
        listDomainsInFunction = functionsDomainsRepository.findAllById(domainid);

        return listDomainsInFunction;
    }

    @GetMapping(path = "/alldomains")
    public @ResponseBody
    Iterable<Domains> getAllDomains() {
        return domainRepository.findAll();
    }

    @GetMapping(path = "/allscores")
    public @ResponseBody
    Iterable<Scores> getAllScores() {
        return scoreRepository.findAll();
    }

    //@GetMapping(path = "/userbyid")
    //public @ResponseBody
    //Employees getUsersById(@RequestParam int id) {
    //    return employeesRepository.findOne(1);
//
    //}

    @GetMapping(path = "/domainsbyid")
    public @ResponseBody
    Iterable<Domains> getDomainsById(@RequestParam int id) {
        return domainRepository.findById(id);
    }


}