package com.SDP.BLL;

import com.SDP.Models.Employees;
import com.SDP.Models.Scores;
import com.SDP.Models.ScoresObject;
import com.SDP.Repositories.EmployeesRepository;
import com.SDP.Repositories.ScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if(scores.size() >= 1 && scores.get(0) != null){
            obj.setDomain1(scores.get(0).getDomain());
            obj.setScore1(scores.get(0));
        }

        if(scores.size() >= 2 && scores.get(1) != null){
            obj.setDomain2(scores.get(1).getDomain());
            obj.setScore2(scores.get(1));
        }

        if(scores.size() >= 3 && scores.get(2) != null){
            obj.setDomain3(scores.get(2).getDomain());
            obj.setScore3(scores.get(2));
        }

        if(scores.size() >= 4 && scores.get(3) != null) {
            obj.setDomain4(scores.get(3).getDomain());
            obj.setScore4(scores.get(3));
        }

        if(scores.size() >= 5 && scores.get(4) != null){
            obj.setDomain5(scores.get(4).getDomain());
            obj.setScore5(scores.get(4));
        }

        return obj;
    }
}
