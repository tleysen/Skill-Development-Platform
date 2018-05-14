package com.SDP.Controllers;

import com.SDP.BLL.CourseRecommendation;
import com.SDP.Models.Courses;
import com.SDP.Models.EmployeeCourses;
import com.SDP.Repositories.CoursesRepository;
import com.SDP.Repositories.EmployeeCoursesRepository;
import com.SDP.Repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/req") // This means URL's start with /XXX (after Application path)
public class CourseController {

    @Autowired
    private EmployeeCoursesRepository ecr;
    @Autowired
    private CourseRecommendation crmd;
    @Autowired
    private CoursesRepository cr;
    @Autowired
    private EmployeesRepository er;

    @GetMapping(path = "/coursesbydomainid/{id}")
    public @ResponseBody
    List<Courses> getCoursesForDomain(@PathVariable("id") String id) {
        return crmd.GetCoursesForDomain(Integer.parseInt(id));
    }

    @GetMapping(path = "/recommendCourse/{id_emp}/{func_id}")
    public @ResponseBody
    List<Courses> getCoursesForEmp(@PathVariable("id_emp") String id_emp, @PathVariable("func_id") String func_id) {
        return crmd.RecommendByPriorityByEmployeeId(Integer.parseInt(id_emp), Integer.parseInt(func_id));
    }

    @RequestMapping(value = "/coursesbyemployee/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<Courses> coursesByEmployee(
            @PathVariable("id") String id) {
        return crmd.AllCoursesByEmployeeId(Integer.parseInt(id));
    }

    @GetMapping(value= "/completedcoursesbyemployee/{id}/{func_id}")
    public @ResponseBody
    List<Courses> getCompletedCoursesByEmployee(
            @PathVariable("id") String id,
            @PathVariable("func_id") String func_id){
        return crmd.GetCompletedCoursesByEmployeeIdAndFuncId(Integer.parseInt(id), Integer.parseInt(func_id));
    }

    @GetMapping(value= "/incompletedcoursesbyemployee/{id}/{func_id}")
    public @ResponseBody
    List<Courses> getIncompletedCoursesByEmployee(
            @PathVariable("id") String id,
            @PathVariable("func_id") String func_id
    ){
        return crmd.GetIncompletedCoursesByEmployeeIdAndFuncId(Integer.parseInt(id), Integer.parseInt(func_id));
    }

    @RequestMapping(value = "/completecourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewDomain(
            @RequestParam("emp_id") String emp_id,
            @RequestParam("c_id") String courseId,
            @RequestParam("date") String date) throws ParseException {
        crmd.moveCourseToCompleted(Integer.parseInt(emp_id), Integer.parseInt(courseId), date);
    }

    @RequestMapping(value = "/followcourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void followCourseById(
            @RequestParam("e_id") String empId,
            @RequestParam("c_id") String courseId) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        date.setTime(0);

        EmployeeCourses ec = new EmployeeCourses();
        ec.setCourse(cr.findById(Integer.parseInt(courseId)));
        ec.setEmployee(er.findById(Integer.parseInt(empId)));
        ec.setCompletion_date(new java.sql.Date(date.getTime()));
        ecr.save(ec);
    }




}
