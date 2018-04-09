package com.SDP.Controllers;


import com.SDP.BLL.Settings;
import com.SDP.Remaining.PropertyReader;
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
    //*****                                     GET DB Settings                                                    *****
    //------------------------------------------------------------------------------------------------------------------

    @GetMapping(path = "/getsettings")
    public @ResponseBody
    Settings getSetings() {
        return appSettings;
    }


    //------------------------------------------------------------------------------------------------------------------
    //****                                  DB PARAMETER POST                                                      *****
    //------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/changeDb", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void changeDb(
            @RequestParam("user") String user,
            @RequestParam("pass") String pass,
            @RequestParam("url") String url) {

        if(!user.equals("")){
            appSettings.setUser(user);
        }
        if(!pass.equals("")){
            appSettings.setDbpw(pass);
        }
        if(!url.equals("")){
            appSettings.setUrl(url);
        }
    }



    //------------------------------------------------------------------------------------------------------------------
    //****                                       EXP PARAMETER POST                                                *****
    //------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/changeExp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void changeExp(
            @RequestParam("juniorMedior") String juniorMedior,
            @RequestParam("medior") String medior,
            @RequestParam("mediorSenior") String mediorSenior,
            @RequestParam("senior") String senior,
            @RequestParam("factor") String factor) {

        if(!juniorMedior.equals("")){
            PropertyReader.setJuniorMedior(Integer.parseInt(juniorMedior));
        }
        if(!medior.equals("")){
            PropertyReader.setMedior(Integer.parseInt(medior));
        }
        if(!mediorSenior.equals("")){
            PropertyReader.setMediorSenior(Integer.parseInt(mediorSenior));
        }
        if(!senior.equals("")){
            PropertyReader.setSenior(Integer.parseInt(senior));
        }
        if(!factor.equals("")){
            PropertyReader.setExponentialFactor(Double.parseDouble(factor));
        }
    }

}
