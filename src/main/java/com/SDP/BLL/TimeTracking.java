package com.SDP.BLL;

import com.SDP.Models.Domains;
import com.SDP.Models.FunctionsDomains;
import com.SDP.Models.Scores;
import com.SDP.Models.TimeTrackingObject;
import com.SDP.Repositories.FunctionsDomainsRepository;
import com.SDP.Repositories.ScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TimeTracking {


    @Autowired
    ScoresRepository sr;
    @Autowired
    FunctionsDomainsRepository fdr;
    @Autowired
    ExperienceCalculation ec;


    // WORKING
    private List<Scores> getScoresForEmployeeWithFunction(int emp_id, int func_id){

        List<FunctionsDomains> functionsDomains = fdr.findAllByFunction_Id(func_id);
        List<Domains> coupledDomains = new ArrayList<>();
        List<Scores> allScores = sr.findByEmployee_IdOrderByDateDescIdDesc(emp_id);
        List<Scores> filteredScores = new ArrayList<>();


        for(FunctionsDomains fd : functionsDomains){
            coupledDomains.add(fd.getDomain());
        }

        for(Scores s : allScores){
            if(coupledDomains.contains(s.getDomain())){
                filteredScores.add(s);
            }
        }

        return filteredScores;
    }

    private List<Integer> format1DomainScoresTo12Months(List<Scores> list){

        Date arrayDate = new Date();
        Date dateMinusYear = new Date();
        List<Integer> out = new ArrayList<>();
        List<Scores> filtered = new ArrayList<>();
        Calendar baseCal = Calendar.getInstance();
        Scores currentScoreObj = new Scores();
        int startingScore;

        out.add(0);


        baseCal.add(Calendar.MONTH, -10);
        baseCal.set(Calendar.DAY_OF_MONTH, 1);
        dateMinusYear = baseCal.getTime();

        baseCal = Calendar.getInstance();

        startingScore = 0;

        //filter out the scores from before 12 months ago and set the latest as the starting score
        for(Scores s : list){
            if(s.getDate().before(dateMinusYear)){
                startingScore = s.getPoints();
                out.set(0, startingScore);
            }
            else{
                filtered.add(s);
            }
        }

        arrayDate = dateMinusYear;


        currentScoreObj.setPoints(startingScore);
        currentScoreObj.setDate(new java.sql.Date(arrayDate.getTime()));


        for(int i = 0; i < 11; i++){
            for(Scores s : filtered){
                if(arrayDate.getMonth() == s.getDate().getMonth() && arrayDate.getYear() == s.getDate().getYear()&& currentScoreObj.getDate().before(s.getDate())){
                    currentScoreObj.setPoints(s.getPoints());
                    currentScoreObj.setDate(s.getDate());
                }
            }
            baseCal.setTime(arrayDate);
            baseCal.add(Calendar.MONTH, 1);
            arrayDate = baseCal.getTime();
            out.add(currentScoreObj.getPoints());
        }
        return out;
    }

    public TimeTrackingObject getAllSetsForEmployeeWithFunction(int emp_id, int func_id){

        List<Scores> allScoresForEmployee = getScoresForEmployeeWithFunction(emp_id, func_id);
        List<FunctionsDomains> functionsDomains = fdr.findAllByFunction_Id(func_id);
        List<Scores> input = new ArrayList<>();
        List<List<Integer>> wa_scorearray = new ArrayList<>();
        List<String> wa_labelarray = new ArrayList<>();
        TimeTrackingObject out = new TimeTrackingObject();

        for(FunctionsDomains fd : functionsDomains){
            ec.checkCurrentScoreVsOldScore(emp_id, fd.getDomain().getId());
            for(Scores s : allScoresForEmployee){
                if(fd.getDomain() == s.getDomain()){
                    input.add(s);
                }
            }

            wa_labelarray.add(fd.getDomain().getName());
            wa_scorearray.add(format1DomainScoresTo12Months(input));
            input.clear();

        }

        out.setDatasets(wa_scorearray);
        out.setDatalabels(wa_labelarray);

        return out;
    }

}
