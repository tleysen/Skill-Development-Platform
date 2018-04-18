package com.SDP.Controllers;

import com.SDP.BLL.ExperienceCalculation;
import com.SDP.BLL.TimeTracking;
import com.SDP.Models.ExperienceObject;
import com.SDP.Models.TimeTrackingObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/req")
public class ExperienceController {

    @Autowired
    ExperienceCalculation ec;
    @Autowired
    TimeTracking tt;

    @RequestMapping(value = "/expforempfunc/{id}/{func}", method = RequestMethod.GET)
    public @ResponseBody
    ExperienceObject getExpObjForEmployee(
            @PathVariable("id") String id, @PathVariable("func") String func) {
        return  ec.calculateProfile(Integer.parseInt(id), func);
    }

    @RequestMapping(value = "/timetracking/{id}/{func}", method = RequestMethod.GET)
    public @ResponseBody
    TimeTrackingObject getTimeObjForEmployee(
            @PathVariable("id") String id, @PathVariable("func") String func) {
        return tt.getAllSetsForEmployeeWithFunction(Integer.parseInt(id),Integer.parseInt(func));
    }

}
