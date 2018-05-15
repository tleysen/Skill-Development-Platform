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
    @Autowired
    private DomainsRepository dr;
    @Autowired
    private EmployeesRepository er;

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
            List<FunctionsDomains> allFunctionDomains = fdr.findAllByFunction_Id(ef.getFunction().getId());
            for(FunctionsDomains fd : allFunctionDomains){
                checkCurrentScoreVsOldScore(employee_id, fd.getDomain().getId());
            }
            totalExp += ef.getExpboost();
        }
        return totalExp;
    }

    private int calculateTotalExperiencepointsForFunction(int employee_id, int func_id){
        int totalExp = 0;
        //All bound domains for given function
        List<FunctionsDomains> boundDomains = fdr.findAllByFunction_Id(func_id);
        //find all courses for bound domains
        for(FunctionsDomains fd: boundDomains) {
            checkCurrentScoreVsOldScore(employee_id, fd.getDomain().getId());
            //calculate the total exp for function
            totalExp += calculateTotalExpForDomain(employee_id, fd.getDomain().getId());
        }
        EmployeesFunctions employeesFunction = efr.findByEmployee_IdAndFunction_Id(employee_id, func_id);
        totalExp += employeesFunction.getExpboost();
        return totalExp;
    }

    private ExperienceObject createExpObject(ExperienceObject obj){

        PropertyReader.Read();

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

    private ExperienceObject calculateDomainProfile(int employee_id, int domain_id){
        ExperienceObject calculated_obj = new ExperienceObject();
        calculated_obj.setTotalExp(calculateTotalExpForDomain(employee_id, domain_id));
        return createExpObject(calculated_obj);
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

    public void checkCurrentScoreVsOldScore(int employee_id, int domain_id) {
        Date utilDate = new Date();
        List<Scores> allScores = sr.findByEmployee_IdAndDomain_IdOrderByDateDescIdDesc(employee_id, domain_id);
        int currentScore;
        if(allScores.size() != 0){
            currentScore = sr.findByEmployee_IdAndDomain_IdOrderByDateDescIdDesc(employee_id, domain_id).get(0).getPoints();
        }else{
            currentScore = 0;
        }
        ExperienceObject expObj = calculateDomainProfile(employee_id, domain_id);
            if (expObj.getLevel() != currentScore) {
                Scores scoreObj = new Scores();
                scoreObj.setDomain(dr.findById(domain_id));
                scoreObj.setEmployee(er.findById(employee_id));
                scoreObj.setPoints(expObj.getLevel());
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                scoreObj.setDate(sqlDate);
                sr.save(scoreObj);
            }
    }

    private int calculateTotalExpForDomain(int employeeId, int domainId){
        int exp = 0;
        Date uncompletedDate = new Date();
        uncompletedDate.setTime(0);
        List<Courses> boundCourses = new ArrayList<>();
        List<Courses> wa_courses;
        List<Courses> followedCourses = new ArrayList<>();
        List<EmployeeCourses> wa_emplcourses;

        boundCourses = cr.findAllByDomain_Id(domainId);

        //All employee courses objects
        wa_emplcourses = ecr.findAllByEmployee_Id(employeeId);
        //convert list to courses list if it is after the default date
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

        return exp;
    }

}
