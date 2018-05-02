package com.SDP.Controllers;

import com.SDP.BLL.CourseRecommendation;
import com.SDP.BLL.CourseRecommendation0;
import com.SDP.Models.Courses;
import com.SDP.Models.Domains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
        return cr.GetCoursesForDomain(Integer.parseInt(id));
    }

    @GetMapping(path = "/recommendCourse/{id_emp}/{func_id}")
    public @ResponseBody
    List<Courses> getCoursesForEmp(@PathVariable("id_emp") String id_emp, @PathVariable("func_id") String func_id) {
        return cr.RecommendByPriorityByEmployeeId(Integer.parseInt(id_emp), Integer.parseInt(func_id));
    }

    @RequestMapping(value = "/coursesbyemployee/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<Courses> coursesByEmployee(
            @PathVariable("id") String id) {
        return cr.AllCoursesByEmployeeId(Integer.parseInt(id));
    }

    @GetMapping(value= "/completedcoursesbyemployee/{id}")
    public @ResponseBody
    List<Courses> getCompletedCoursesByEmployee(@PathVariable("id") String id){
        return cr.GetCompletedCoursesByEmployeeId(Integer.parseInt(id));
    }

    @GetMapping(value= "/incompletedcoursesbyemployee/{id}")
    public @ResponseBody
    List<Courses> getIncompletedCoursesByEmployee(@PathVariable("id") String id){
        return cr.GetIncompletedCoursesByEmployeeId(Integer.parseInt(id));
    }

    @RequestMapping(value = "/completecourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewDomain(
            @RequestParam("emp_id") String emp_id,
            @RequestParam("c_id") String c_id,
            @RequestParam("date") String date) throws ParseException {
        cr.moveCourseToCompleted(Integer.parseInt(emp_id), Integer.parseInt(c_id), date);
    }


}
