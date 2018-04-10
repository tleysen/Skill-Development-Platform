package com.SDP.Controllers;

import com.SDP.BLL.CourseRecommendation0;
import com.SDP.BLL.TopSkills;
import com.SDP.Models.*;
import com.SDP.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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
    private CoursesRepository coursesRepository;
    @Autowired
    private CourseRecommendation0 courseRecommendation;
    @Autowired
    private EmployeesFunctionsRepository employeesFunctionsRepository;
    @Autowired
    private TopSkills topSkills;

    //------------------------------------------------------------------------------------------------------------------
    //*****                                     PARAMLESS GET'S                                                    *****
    //------------------------------------------------------------------------------------------------------------------

    @GetMapping(path = "/allusers")
    public @ResponseBody
    Iterable<Employees> getAllUsers() {
        return employeesRepository.findAll();
    }

    @GetMapping(path = "/alldomains")
    public @ResponseBody
    Iterable<Domains> getAllDomains() {
        return domainsRepository.findAll();
    }

    @GetMapping(path = "/allcourses")
    public @ResponseBody
    Iterable<Courses> getAllCourses() { return coursesRepository.findAll(); }

    @GetMapping(path = "/allscores")
    public @ResponseBody
    Iterable<Scores> getAllScores() {
        return scoreRepository.findAll();
    }

    @GetMapping(path = "/allfunctions")
    public @ResponseBody
    Iterable<Functions> getAllFunctions() { return functionsRepository.findAll(); }

    @GetMapping(path = "/allfunctionsbeta")
    public @ResponseBody
    Iterable<EmployeesFunctions> getAllFunctionsBeta() { return employeesFunctionsRepository.findAll(); }

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

    @RequestMapping(value = "/coursebyid/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Courses getCourseById(
            @PathVariable("id") String id) {
        return coursesRepository.findById(Integer.parseInt(id));
    }

    @RequestMapping(value = "/coursesbyemployee/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<Courses> coursesByEmployee(
            @PathVariable("id") String id) {
        return courseRecommendation.FollowedCoursesEmployees(Integer.parseInt(id));
    }

    @RequestMapping(value = "/scoresforemployee/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<Scores> scoresForEmployee(
            @PathVariable("id") String id) {
        return scoreRepository.findByEmployee_IdOrderByPointsDesc(Integer.parseInt(id));
    }

    @RequestMapping(value = "/topscoresforemployee/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ScoresObject topScoresForEmployee(
            @PathVariable("id") String id) {
        return topSkills.getTop5ForEmployeeId(Integer.parseInt(id));
    }

    @RequestMapping(value = "/functionsforemployee/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<EmployeesFunctions> functionsForEmployee(
            @PathVariable("id") String id) {
        return employeesFunctionsRepository.findAllByEmployee_Id(Integer.parseInt(id));
    }

    //------------------------------------------------------------------------------------------------------------------
    //*****                               PARAMETER GET'S NO RESPONSE                                              *****
    //------------------------------------------------------------------------------------------------------------------


    @RequestMapping(value = "/deleteemployeebyid/{id}", method = RequestMethod.GET)
    public void deleteUserById(
            @PathVariable("id") String id) {
        employeesRepository.deleteById(Integer.parseInt(id));
    }

    @RequestMapping(value = "/deletedomainbyid/{id}", method = RequestMethod.GET)
    public void deleteDomainById(
            @PathVariable("id") String id) {
            domainsRepository.deleteById(Integer.parseInt(id));
    }

    @RequestMapping(value = "/deletecoursebyid/{id}", method = RequestMethod.GET)
    public void deleteCourseById(@PathVariable("id") String id) {
        coursesRepository.deleteById(Integer.parseInt(id));
    }

    @RequestMapping(value = "/deletefunctionbyid/{id}", method = RequestMethod.GET)
    public void deleteFunctionsById(@PathVariable("id") String id) {
        functionsRepository.deleteById(Integer.parseInt(id));
    }


    //------------------------------------------------------------------------------------------------------------------
    //****                                     PARAMETER POST'S                                                    *****
    //------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String addNewEmployee(@RequestParam("name") String name, @RequestParam("lastname") String lastname, @RequestParam("sex")
            String sex, @RequestParam("employee_function") String employee_function, @RequestParam("birth_date")
            String birth_date, @RequestParam("hiring_date") String hiring_date) {

        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);

        //selectedEmployee = employeesRepository.findById(1);
        //employeesFunction = selectedEmployee.getFunction();

        Employees n = new Employees();
        n.setName(name);
        n.setLastname(lastname);
        //n.setFunction(employeesFunction);
        n.setSex(sex);

        try {
            Date parsed_hiring = format.parse(hiring_date);
            n.setHiring_date(new java.sql.Date(parsed_hiring.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            Date parsed_birth = format.parse(birth_date);
            n.setBirth_date(new java.sql.Date(parsed_birth.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        employeesRepository.save(n);
        System.out.println(n);
        return "ok";
    }

}