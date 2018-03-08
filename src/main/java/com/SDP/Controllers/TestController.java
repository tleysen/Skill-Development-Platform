package com.SDP.Controllers;

import com.SDP.BLL.CourseRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/test")
public class TestController {

    @Autowired
    private CourseRecommendation courseRecommendation;


    @RequestMapping(value = "/check/1", method = RequestMethod.GET)
    public @ResponseBody
    Boolean testMeth1() {
        return courseRecommendation.checkIfCourseNotFollowed(1,1);
    }

    @RequestMapping(value = "/check/2", method = RequestMethod.GET)
    public @ResponseBody
    Boolean testMeth2() {
        return courseRecommendation.checkIfCourseNotFollowed(1,2);
    }
}
