package com.SDP.BLL;

import com.SDP.Models.Courses;
import com.SDP.Models.EmployeeCourses;
import com.SDP.Models.Employees;
import com.SDP.Repositories.EmployeeCoursesRepository;
import com.SDP.Repositories.EmployeesRepository;
import com.SDP.Repositories.ScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExperienceCalculation {


    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private ScoresRepository scoresRepository;
    @Autowired
    private EmployeeCoursesRepository employeeCoursesRepository;


    public int calculateTotalExperiencepoints(int employee_id){ //ADD FUNCTION

        Employees selectedEmployee;
        List<EmployeeCourses> all_employeeCourses;
        List<Courses> followed_courses = new ArrayList<Courses>();
        int total_exp = 0;

        all_employeeCourses = employeeCoursesRepository.findAllByEmployee_Id(employee_id);

        for(EmployeeCourses employeeCourse: all_employeeCourses){
            followed_courses.add(employeeCourse.getCourse());
            total_exp += employeeCourse.getCourse().getExp();
        }

        return total_exp;
    }

}
