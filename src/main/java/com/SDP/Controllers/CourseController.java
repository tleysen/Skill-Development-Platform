package com.SDP.Controllers;

import com.SDP.BLL.CourseRecommendation;
import com.SDP.BLL.CourseRecommendation0;
import com.SDP.Models.Courses;
import com.SDP.Models.Domains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="/req") // This means URL's start with /XXX (after Application path)
public class CourseController {

    @Autowired
    private CourseRecommendation0 courseRecommendation;
    @Autowired
    private CourseRecommendation cr;

    @GetMapping(path = "/domainbyemployeeid/{id}")
    public @ResponseBody
    List<Domains> getDomainsForEmployeeId(@PathVariable("id") String id) {
        return courseRecommendation.GetDomainsForEmployeeId(Integer.parseInt(id));
    }

    @GetMapping(path = "/coursesbydomainid/{id}")
    public @ResponseBody
    List<Courses> getCoursesForDomain(@PathVariable("id") String id) {
        return courseRecommendation.FollowedCoursesEmployees(Integer.parseInt(id));
    }


    @GetMapping(path = "/recommendCourse/{id_emp}/{func_id}")
    public @ResponseBody
    List<Courses> getCoursesForEmp(@PathVariable("id_emp") String id_emp, @PathVariable("func_id") String func_id) {
        return cr.RecommendByPriorityByEmployeeId(Integer.parseInt(id_emp), Integer.parseInt(func_id));
    }
}
