package com.SDP.Controllers;


import com.SDP.BLL.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/props")
public class PropertiesController {

    @Autowired
    private Settings appSettings;


    //------------------------------------------------------------------------------------------------------------------
    //*****                                     PARAMLESS GET'S                                                    *****
    //------------------------------------------------------------------------------------------------------------------
    @GetMapping(path = "/getDbPass")
    public @ResponseBody
    String getDbPass(){ return appSettings.getDbpw(); }

    @GetMapping(path = "/getDbIp")
    public @ResponseBody
    String getDbIp(){ return appSettings.getUrl(); }

    @GetMapping(path = "/dgetDbUser")
    public @ResponseBody
    String getDbUser(){ return appSettings.getUser(); }



    //------------------------------------------------------------------------------------------------------------------
    //****                                     PARAMETER POST'S                                                    *****
    //------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/newDbPass", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String changeDbPass(@RequestParam("data") String data) {
        appSettings.setDbpw(data);
        return "ok";
    }

    @RequestMapping(value = "/newDbIp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String changeDbIp(@RequestParam("data") String data) {
        appSettings.setUrl(data);
        return "ok";
    }

    @RequestMapping(value = "/newDbUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String changeDbUser(@RequestParam("data") String data) {
        appSettings.setUrl(data);
        return "ok";
    }

}
