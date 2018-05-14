package com.SDP.BLL;

import com.SDP.Models.*;
import com.SDP.Remaining.PropertyReader;
import com.SDP.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExperienceCalculation {

    @Autowired
    private EmployeesFunctionsRepository efr;
    @Autowired
    private EmployeeCoursesRepository ecr;
    @Autowired
    private FunctionsDomainsRepository fdr;
    @Autowired
    private CoursesRepository cr;
    @Autowired
    private ScoresRepository sr;


    PropertyReader pr = new PropertyReader();



    private int calculateTotalExp(int employee_id){
        Date uncompletedDate = new Date();
        uncompletedDate.setTime(0);
        List<EmployeeCourses> employeeCoursesList;
        int totalExp = 0;
        List<EmployeesFunctions> employeesFunctionsList;

        //All employee courses objects
        employeeCoursesList = ecr.findAllByEmployee_Id(employee_id);
        //convert list to courses list
        for (EmployeeCourses ec: employeeCoursesList){
            Date courseDate = ec.getCompletion_date();
            if(courseDate.after(uncompletedDate)){
                totalExp += ec.getCourse().getExp();
            }
        }

        employeesFunctionsList = efr.findAllByEmployee_Id(employee_id);
        for(EmployeesFunctions ef : employeesFunctionsList){
            totalExp += ef.getExpboost();
        }
        return totalExp;
    }

    private int calculateTotalExperiencepointsForFunction(int employee_id, int func_id){

        int exp = 0;
        Date uncompletedDate = new Date();
        uncompletedDate.setTime(0);
        List<FunctionsDomains> boundDomains;
        List<Courses> boundCourses = new ArrayList<>();
        List<Courses> wa_courses;
        List<Courses> followedCourses = new ArrayList<>();
        List<EmployeeCourses> wa_emplcourses;

        //All bound domains for given function
        boundDomains = fdr.findAllByFunction_Id(func_id);
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
            Date courseDate = ec.getCompletion_date();
            if(courseDate.after(uncompletedDate)) {
                followedCourses.add(ec.getCourse());
            }
        }
        //compare 2 lists
        for(Courses c:boundCourses){
            if(followedCourses.contains(c)){
                //add matching course's exp points to total
                exp += c.getExp();
            }
        }
        EmployeesFunctions employeesFunction = efr.findByEmployee_IdAndFunction_Id(employee_id, func_id);

        exp += employeesFunction.getExpboost();

        return exp;
    }

    private ExperienceObject createExpObject(ExperienceObject obj){

        pr.Read();

        double FACTOR = PropertyReader.getExponentialFactor();
        int BASE_EXP = PropertyReader.getBaseExp();
        int JUNIOR_MEDIOR = PropertyReader.getJuniorMedior();
        int MEDIOR = PropertyReader.getMedior();
        int MEDIOR_SENIOR = PropertyReader.getMediorSenior();
        int SENIOR = PropertyReader.getSenior();

        int totalExp;
        int level = 1;
        int requiredExp = BASE_EXP;



        totalExp = obj.getTotalExp();

        if(totalExp >=requiredExp){
            do{
                totalExp = totalExp - requiredExp;
                level++;
                requiredExp =  (int) (requiredExp + requiredExp * FACTOR);
                requiredExp = requiredExp - (requiredExp % 100);
            }while(totalExp>= requiredExp);
        }

        obj.setRemainingExp(totalExp);
        obj.setLevel(level);

        if(level > SENIOR){
            obj.setTitle("Senior");
        }
        else if(level >= MEDIOR_SENIOR) {
            obj.setTitle("Medior-Senior");
        }
        else if(level >= MEDIOR) {
            obj.setTitle("Medior");
        }
        else if(level >= JUNIOR_MEDIOR) {
            obj.setTitle("Junior-Medior");
        }
        else {
            obj.setTitle("Junior");
        }

        obj.setRequiredExp(requiredExp);
        return obj;
    }

    public ExperienceObject calculateFunctionProfile(int employee_id, int func_id){
        ExperienceObject calculated_obj = new ExperienceObject();
        calculated_obj.setTotalExp(calculateTotalExperiencepointsForFunction(employee_id, func_id));
        return createExpObject(calculated_obj);

    }

    public ExperienceObject calculateGeneralProfile(int employee_id){
        ExperienceObject calculated_obj = new ExperienceObject();
        calculated_obj.setTotalExp(calculateTotalExp(employee_id));
        return createExpObject(calculated_obj);

    }

    public void calculateDomainScoresForEmployeeWithFunctions(int employee_id, int func_id){
        List<FunctionsDomains> allFunctionDomains = fdr.findAllByFunction_Id(1);
        List<Domains> allDomains = new ArrayList<>();
        Scores currentScore = sr.findByEmployee_IdAndDomain_IdOrderByDateDesc(employee_id, func_id).get(0);
        for(FunctionsDomains fd : allFunctionDomains){
            allDomains.add(fd.getDomain());
        }
    }

}
