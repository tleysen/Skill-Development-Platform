package com.SDP.Controllers;

import com.SDP.BLL.ExperienceCalculation;
import com.SDP.BLL.TimeTracking;
import com.SDP.Models.EmployeesFunctions;
import com.SDP.Models.ExperienceObject;
import com.SDP.Models.TimeTrackingObject;
import com.SDP.Repositories.EmployeesFunctionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/req")
public class ExperienceController {

    @Autowired
    ExperienceCalculation ec;
    @Autowired
    TimeTracking tt;
    @Autowired
    EmployeesFunctionsRepository efr;

    @RequestMapping(value = "/expforempfunc/{id}/{func}", method = RequestMethod.GET)
    public @ResponseBody
    ExperienceObject getExpObjForEmployeeFunction(
            @PathVariable("id") String id,
            @PathVariable("func") String func_id) {
        return  ec.calculateFunctionProfile(Integer.parseInt(id), Integer.parseInt(func_id));
    }

    @RequestMapping(value = "/expforemp/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ExperienceObject getExpObjForEmployee(
            @PathVariable("id") String id){
        return ec.calculateGeneralProfile(Integer.parseInt(id));
    }

    @RequestMapping(value = "/timetracking/{id}/{func}", method = RequestMethod.GET)
    public @ResponseBody
    TimeTrackingObject getTimeObjForEmployee(
            @PathVariable("id") String id, @PathVariable("func") String func) {
        return tt.getAllSetsForEmployeeWithFunction(Integer.parseInt(id),Integer.parseInt(func));
    }

    @RequestMapping(value = "/boost", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewDomain(
            @RequestParam("e_id") String employeeId,
            @RequestParam("f_id") String functionId,
            @RequestParam("amount") String amount){

        EmployeesFunctions ef = efr.findByEmployee_IdAndFunction_Id(Integer.parseInt(employeeId), Integer.parseInt(functionId));
        ef.setExpboost(Integer.parseInt(amount));
        efr.save(ef);
    }
}
