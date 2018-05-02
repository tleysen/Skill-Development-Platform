package com.SDP.BLL;

import com.SDP.Models.*;
import com.SDP.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseRecommendation {

    @Autowired
    EmployeesRepository er;
    @Autowired
    EmployeesFunctionsRepository efr;
    @Autowired
    FunctionsDomainsRepository fdr;
    @Autowired
    CoursesRepository cr;
    @Autowired
    EmployeeCoursesRepository ecr;
    @Autowired
    FunctionsRepository fr;



    public List<Courses> RecommendByPriorityByEmployeeId(int employee_id, int function_id){

        boolean courseFound = false;
        int functionCounter = 0;
        int domainCounter = 0;

        Functions selectedFunction;
        Employees selectedEmployee = er.findById(employee_id);
        List<FunctionsDomains> functionsDomainsList = null;
        List<Courses> boundCourses;
        List<Courses> followedCourses = new ArrayList<Courses>();
        List<EmployeeCourses> employeeCoursesList;
        List<Courses> filteredCourses = new ArrayList<Courses>();


        do{
            //get domains by
            functionsDomainsList = fdr.findAllByFunction_IdOrderByDomainPriorityDesc(function_id);
            //select the first domain in the list
            Domains selectedDomain = functionsDomainsList.get(domainCounter).getDomain();
            //find courses connected to domain
            boundCourses = cr.findAllByDomain_Id(selectedDomain.getId());
            //get followed courses
            employeeCoursesList = ecr.findAllByEmployee_Id(employee_id);
            //Extract the courses from the EmployeeCourses Objects
            for(EmployeeCourses ec : employeeCoursesList){
                followedCourses.add(ec.getCourse()); }//Check if courses are followed by comparing ID's
            for(Courses bc : boundCourses){
                if(!followedCourses.contains(bc)){
                    filteredCourses.add(bc); } }//check if a course is found
            if(filteredCourses.size() > 0){
                courseFound = true;
            }
            domainCounter++;
        }while(!courseFound);
        return filteredCourses;
    }

    public List<Courses> AllCoursesByEmployeeId(int id){

        List<EmployeeCourses> employeeCoursesList = new ArrayList<EmployeeCourses>();
        List<Courses> followedCoursesList = new ArrayList<Courses>();
        Iterable<Courses>allCoursesIterable;

        //empty variables
        followedCoursesList.clear();
        //insert all Course objects into an Iterable object
        allCoursesIterable = cr.findAll();
        //insert all EmployeeCourse objects which match the input employeeid into a List
        employeeCoursesList = ecr.findAllByEmployee_Id(id);

        //iterate over EmployeeCourses objects
        for (EmployeeCourses employeeCourses : employeeCoursesList) {
            //iterate over Courses objects
            for (Courses course : allCoursesIterable){
                //check if courseid matches a value in the followed courses id
                if(course.getId() == employeeCourses.getCourse().getId()){
                    // add the Course object to the followed courses list
                    followedCoursesList.add(employeeCourses.getCourse());
                }
            }
        }

        //return result
        return followedCoursesList;
    }

    public List<Courses> GetCoursesForDomain(int id) {

        //fill courseslist with courses where domain id matches
        List<Courses> coursesList = cr.findAllByDomain_Id(id);
        //return result
        return coursesList;
    }

    public List<Courses> GetCompletedCoursesByEmployeeId(int id){

        Date uncompletedDate = new Date();
        uncompletedDate.setTime(0);
        List<Courses> completedCourses = new ArrayList<>();
        //All employee courses objects
        List<EmployeeCourses> wa_emplcourses = ecr.findAllByEmployee_Id(id);
        // Check if course completion date is default, if not add to completed list
        for (EmployeeCourses ec: wa_emplcourses){
            Date courseDate = ec.getCompletion_date();
            if(courseDate.after(uncompletedDate)){
                completedCourses.add(cr.findById(ec.getCourse().getId()));
            }
        }
        return completedCourses;
    }

    public List<Courses> GetIncompletedCoursesByEmployeeId(int id){

        Date uncompletedDate = new Date();
        uncompletedDate.setTime(0);
        List<Courses> incompletedCourses = new ArrayList<>();
        //All employee courses objects
        List<EmployeeCourses> wa_emplcourses = ecr.findAllByEmployee_Id(id);
        // Check if course completion date is default, if not add to completed list
        for (EmployeeCourses ec: wa_emplcourses){
            Date courseDate = ec.getCompletion_date();
            if(courseDate.before(uncompletedDate)){
                incompletedCourses.add(cr.findById(ec.getCourse().getId()));
            }
        }
        return incompletedCourses;
    }

    public void moveCourseToCompleted(int emp_id, int c_id, String date) throws ParseException {
        EmployeeCourses ecrObj = ecr.findByEmployee_IdAndCourse_Id(emp_id, c_id);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateObj = dateFormat.parse(date);
            ecrObj.setCompletion_date(new java.sql.Date(dateObj.getTime()));
        ecr.save(ecrObj);
    }

}