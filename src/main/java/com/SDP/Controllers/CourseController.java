package com.SDP.Controllers;

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

    @GetMapping(path = "/recommendCourse/{id}")
    public @ResponseBody
    String getCoursesForEmp(@PathVariable("id") String id) {
        return courseRecommendation.RecommendCourseByPriority(Integer.parseInt(id));
    }
}
