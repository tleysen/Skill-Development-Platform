package com.SDP.Controllers;


import com.SDP.BLL.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/props")
public class PropertiesController {

    @Autowired
    private Settings appSettings;


    @GetMapping(path = "/datapw")
    public @ResponseBody
    String getDataPw(){ return appSettings.getDbpw(); }
}
