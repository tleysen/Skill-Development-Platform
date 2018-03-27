package com.SDP.BLL;

import com.SDP.Models.Employees;
import com.SDP.Models.Scores;
import com.SDP.Models.TestObject;
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




    public TestObject getTop5ForEmployeeId(int id){

        Employees emp = employeesRepository.findById(id);
        TestObject obj = new TestObject();
        obj.setEmp(emp);
        List<Scores> scores = scoresRepository.findByEmployee_IdOrderByPointsDesc(id);

        if(scores.size() >= 1 && scores.get(0).getDomain() != null){
            obj.setDomain1(scores.get(0).getDomain());
        }

        if(scores.size() >= 2 && scores.get(1).getDomain() != null){
            obj.setDomain2(scores.get(1).getDomain());
        }

        if(scores.size() >= 3 && scores.get(2).getDomain() != null){
            obj.setDomain3(scores.get(2).getDomain());
        }

        if(scores.size() >= 4 && scores.get(3).getDomain() != null) {
            obj.setDomain4(scores.get(3).getDomain());
        }

        if(scores.size() >= 5 && scores.get(4).getDomain() != null){
            obj.setDomain5(scores.get(4).getDomain());
        }

        return obj;
    }
}
