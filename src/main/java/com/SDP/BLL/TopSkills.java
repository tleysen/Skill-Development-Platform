package com.SDP.BLL;

import com.SDP.Models.Employees;
import com.SDP.Models.Scores;
import com.SDP.Models.ScoresObject;
import com.SDP.Repositories.EmployeesRepository;
import com.SDP.Repositories.ScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopSkills {


    @Autowired
    EmployeesRepository employeesRepository;
    @Autowired
    ScoresRepository scoresRepository;




    public ScoresObject getTop5ForEmployeeId(int id){

        Employees emp = employeesRepository.findById(id);
        ScoresObject obj = new ScoresObject();
        obj.setEmp(emp);
        List<Scores> scores = scoresRepository.findByEmployee_IdOrderByPointsDesc(id);
        List<Scores> filtered_scores = new ArrayList<>();
        boolean domainPresent = false;

        // Check if domain is already present in TOP5 list
        for(Scores s : scores){
            for(Scores fs : filtered_scores)
            {
                if(fs.getDomain() == s.getDomain()){
                    domainPresent = true;
                }

            }
            if(!domainPresent){
                filtered_scores.add(s);
            }
            domainPresent = false;
        }

        if(filtered_scores.size() >= 1 && filtered_scores.get(0) != null){
            obj.setDomain1(filtered_scores.get(0).getDomain());
            obj.setScore1(filtered_scores.get(0));
        }

        if(filtered_scores.size() >= 2 && filtered_scores.get(1) != null){
            obj.setDomain2(filtered_scores.get(1).getDomain());
            obj.setScore2(filtered_scores.get(1));
        }

        if(filtered_scores.size() >= 3 && filtered_scores.get(2) != null){
            obj.setDomain3(filtered_scores.get(2).getDomain());
            obj.setScore3(filtered_scores.get(2));
        }

        if(filtered_scores.size() >= 4 && filtered_scores.get(3) != null) {
            obj.setDomain4(filtered_scores.get(3).getDomain());
            obj.setScore4(filtered_scores.get(3));
        }

        if(filtered_scores.size() >= 5 && filtered_scores.get(4) != null){
            obj.setDomain5(filtered_scores.get(4).getDomain());
            obj.setScore5(filtered_scores.get(4));
        }
        return obj;
    }
}
