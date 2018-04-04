package com.SDP.Controllers;

import com.SDP.BLL.ExperienceCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/req")
public class ExperienceController {

    @Autowired
    ExperienceCalculation ec;

}
