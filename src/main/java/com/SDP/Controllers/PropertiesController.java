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
    //*****                                     PARAMLESS GET                                                    *****
    //------------------------------------------------------------------------------------------------------------------

    @GetMapping(path = "/getsettings")
    public @ResponseBody
    Settings getSetings() {
        return appSettings;
    }


    //------------------------------------------------------------------------------------------------------------------
    //****                                     PARAMETER POST'S                                                    *****
    //------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/newdbpass", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String changeDbPass(@RequestParam("data") String data) {
        appSettings.setDbpw(data);
        return "ok";
    }

    @RequestMapping(value = "/newDbIp/{data}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String changeDbIp(@RequestParam("data") String data) {
        appSettings.setUrl(data);
        return "ok";
    }

    @RequestMapping(value = "/newDbUser/{data}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String changeDbUser(@RequestParam("data") String data) {
        appSettings.setUrl(data);
        return "ok";
    }

    //------------------------------------------------------------------------------------------------------------------
    //****                                       EXP PARAMETER POST'S                                              *****
    //------------------------------------------------------------------------------------------------------------------


}
