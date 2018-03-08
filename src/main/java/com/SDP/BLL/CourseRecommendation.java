package com.SDP.BLL;

import com.SDP.Models.*;
import com.SDP.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CourseRecommendation {

    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private EmployeeCoursesRepository employeeCoursesRepository;
    @Autowired
    private FunctionsDomainsRepository functionsDomainsRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private DomainsRepository domainsRepository;


    private List<FunctionsDomains> functionsDomainsList = new ArrayList<FunctionsDomains>();
    private List<Domains> domainsList = new ArrayList<Domains>();
    private List<String> domainsIdList = new ArrayList<String>();
    private List<Courses> coursesList = new ArrayList<Courses>();
    private List<EmployeeCourses> employeeCoursesList = new ArrayList<EmployeeCourses>();
    private List<Courses> followedCoursesList = new ArrayList<Courses>();
    private List<Courses> coursesForDomainList = new ArrayList<Courses>();
    private List<Courses> checkedCoursesForDomainList = new ArrayList<Courses>();
    private List<Employees> allEmployeesList = new ArrayList<Employees>();
    private List<EmployeeCourses> allEmployeeCoursesList = new ArrayList<EmployeeCourses>();
    private Random rand = new Random();

    private String domainid;
    private int functionid;
    private int counter;
    private Courses recommendedCourse;
    private Employees selectedEmployee;
    private Functions employeesFunction;
    private Domains selectedDomain;
    private Employees checkEmployee;
    private Courses checkCourse;
    private Iterable<Employees> allEmployeesIterable;
    private Iterable<EmployeeCourses> allEmployeeCoursesIterable;
    private Iterable<Courses>allCoursesIterable;



    //----------------------------------------------------------------------------------------------------------------//
    //                                              Initialize                                                        //
    //----------------------------------------------------------------------------------------------------------------//
    //                                       Empty all class variables                                                //
    //----------------------------------------------------------------------------------------------------------------//
    private void Init(){

        functionsDomainsList.clear();
        domainsIdList.clear();
        domainsList.clear();
        coursesList.clear();
        employeeCoursesList.clear();
        followedCoursesList.clear();
        allEmployeeCoursesList.clear();
        counter = 0;
        selectedDomain = null;

    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                    RecommendCourseByPriority                                                   //
    //----------------------------------------------------------------------------------------------------------------//
    //                            Retrieve the list of domains for the coupled function                               //
    //----------------------------------------------------------------------------------------------------------------//
    public String RecommendCourseByPriority(int employee_id) {

        //empty variables
        Init();


        //Populate List with domains coupled to employee
        domainsList = GetDomainsForEmployeeId(employee_id);
        System.out.println("Domains for employee: " + domainsList);

        //Iterate over domains
        for (Domains domain : domainsList) {
            System.out.println("Iterating domain: " + domain);
            //check if a domain is set, then check priority
            if (selectedDomain == null || domain.getPriority() > selectedDomain.getPriority()) {
                //if a domain is not set or priority is higher, overwrite
                selectedDomain = domain;
                System.out.println("selected domain: " + selectedDomain);
            }
        }

        //Populate list with courses for selected domain
        coursesForDomainList = GetCoursesForDomain(selectedDomain.getId());

        //----------------------------------------------------------------------------------------------------------------//
        //                                                                                                                //
        //                                  SUPER VAGE ERROR SEND HELP                                                    //
        //                (als uitprinten, geen null, als niet uitprinten, wel null)                                      //
        //----------------------------------------------------------------------------------------------------------------//

        System.out.println("getCoursesForDomain: " + GetCoursesForDomain(selectedDomain.getId()));

        counter = 0;
        try{
            for(Courses course: coursesForDomainList){
                System.out.println("course to check: " + course);
                if(checkIfCourseNotFollowed(employee_id, course.getId())){
                    checkedCoursesForDomainList.add(counter, course);
                    System.out.println("course: " + course + "counter: " + counter);
                    counter++;
                }
            }
        }catch (Error e){
            return e.getStackTrace().toString();
        }
        //check if there are any courses found
        if (coursesForDomainList.size() > 1) {
            //if courses are found, select one at random
            int rnd = rand.nextInt(coursesForDomainList.size());
            recommendedCourse = coursesForDomainList.get(rnd);
        } else if (coursesForDomainList.size() == 1) {
            //if only one course is found, return that course
            recommendedCourse = coursesForDomainList.get(0);
        } else {
            System.out.println("--------------------------END-------------------------");
            //if none are found, inform user
            return "No courses found";
        }
        System.out.println("--------------------------END-------------------------");
        //return result
        return recommendedCourse.toString();
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                      CoupledDomainsByFunctionId                                                //
    //----------------------------------------------------------------------------------------------------------------//
    //                            Retrieve the list of domains for the coupled function                               //
    //----------------------------------------------------------------------------------------------------------------//
    private List<Domains> CoupledDomainsByFunctionid(int id){
        // empty variables
        //Init();

        // fill the list with joint objects
        functionsDomainsList = functionsDomainsRepository.findAllByFunction_Id(id);

        // Extract the domain ids from the joint list
        for (FunctionsDomains functiondomain : functionsDomainsList) {
            //get the ID and add it to the list on the index of the counter
            domainid = functiondomain.getDomain().getId().toString();
            domainsIdList.add(counter, domainid);
            counter++;
        }

        //reset counter
        counter = 0;

        // Extract the domain objects from the domainID list
        for(String domainid : domainsIdList) {
            domainsList.add(counter, domainsRepository.findById(Integer.parseInt(domainid)));
            counter++;
        }

        //reset counter
        counter = 0;

        //return result
        return domainsList;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                        CheckIfCourseFollowed                                                   //
    //----------------------------------------------------------------------------------------------------------------//
    //                            Retrieve the list of domains for the coupled function                               //
    //----------------------------------------------------------------------------------------------------------------//
    public boolean checkIfCourseNotFollowed(int employee_id, int course_id) {

        //empty variables
        //Init();

        //insert all employeecourses object into an iterable
        allEmployeeCoursesIterable = employeeCoursesRepository.findAll();

        //convert iterable into List object
        allEmployeeCoursesIterable.forEach(allEmployeeCoursesList::add);

        //run through the list to check if there's a record with both input parameters matching
        for (EmployeeCourses employeeCourse : allEmployeeCoursesList) {
            System.out.println("employeeCourse record: " + employeeCourse);
            if (employeeCourse.getCourse().getId() == course_id && employeeCourse.getEmployee().getId() == employee_id) {
                //return result
                return true;
            }
        }
        //return result
        return false;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                          getCoursesForDomain                                                   //
    //----------------------------------------------------------------------------------------------------------------//
    //                            Retrieve the list of courses for the selected domain                                //
    //----------------------------------------------------------------------------------------------------------------//
    private List<Courses> GetCoursesForDomain(int id){

        //empty variables
        //Init();

        //fill courseslist with courses where domain id matches
        coursesList = coursesRepository.findAllByDomain_Id(id);

        //return result
        return coursesList;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                       GetDomainsForEmployeeId                                                  //
    //----------------------------------------------------------------------------------------------------------------//
    //                              Get all possible domains for a single employee                                    //
    //----------------------------------------------------------------------------------------------------------------//
    public List<Domains> GetDomainsForEmployeeId(int id){

        //empty variables
        //Init();

        //get Employee object by inserted id
        selectedEmployee = employeesRepository.findById(id);

        //get Function object from selected Employee object
        employeesFunction = selectedEmployee.getFunction();

        //get id from resulted Function object
        functionid = employeesFunction.getId();

        //return result
        return CoupledDomainsByFunctionid(functionid);
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                        FollowedCoursesEmployees                                                //
    //----------------------------------------------------------------------------------------------------------------//
    //                            Retrieve the list of courses for the selected domain                                //
    //----------------------------------------------------------------------------------------------------------------//
    public List<Courses> FollowedCoursesEmployees(int id){

        //empty variables
        //Init();

        //insert all Course objects into an Iterable object
        allCoursesIterable = coursesRepository.findAll();
        //insert all EmployeeCourse objects which match the input employeeid into a List
        employeeCoursesList = employeeCoursesRepository.findAllByEmployee_Id(id);

        //iterate over EmployeeCourses objects
        for (EmployeeCourses employeeCourses : employeeCoursesList) {
            //iterate over Courses objects
            for (Courses course : allCoursesIterable){
                //check if courseid matches a value in the followed courses id
                if(course.getId() == employeeCourses.getCourse().getId()){
                    // add the Course object to the followed courses list
                    followedCoursesList.add(employeeCourses.getCourse());
                    System.out.println("followed course: " + employeeCourses.getCourse());
                }
            }
        }

        //return result
        return followedCoursesList;
    }
}