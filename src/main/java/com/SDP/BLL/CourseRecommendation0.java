package com.SDP.BLL;

import com.SDP.Models.*;
import com.SDP.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CourseRecommendation0 {

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
    @Autowired
    private EmployeesFunctionsRepository employeesFunctionsRepository;


    private List<FunctionsDomains> functionsDomainsList = new ArrayList<FunctionsDomains>();
    private List<Domains> domainsList = new ArrayList<Domains>();
    private List<String> domainsIdList = new ArrayList<String>();
    private List<Courses> coursesList = new ArrayList<Courses>();
    private List<Courses> checkedCoursesForDomainList = new ArrayList<Courses>();
    private List<Employees> allEmployeesList = new ArrayList<Employees>();
    private List<EmployeeCourses> allEmployeeCoursesList = new ArrayList<EmployeeCourses>();
    private Random rand = new Random();
    private String domainid;

    private int counter;
    private static Courses recommendedCourse;
    private Functions employeesFunction;
    private Domains selectedDomain;
    private Employees checkEmployee;
    private Courses checkCourse;
    private Iterable<Employees> allEmployeesIterable;



    //----------------------------------------------------------------------------------------------------------------//
    //                                              Initialize                                                        //
    //----------------------------------------------------------------------------------------------------------------//
    //                                       Empty all class variables                                                //
    //----------------------------------------------------------------------------------------------------------------//
    //private void Init(){
//
    //    checkedCoursesForDomainList.clear();
    //    functionsDomainsList.clear();
    //    domainsIdList.clear();
    //    domainsList.clear();
    //    coursesList.clear();
    //    allEmployeeCoursesList.clear();
    //    counter = 0;
    //    selectedDomain = null;
//
    //}

    //----------------------------------------------------------------------------------------------------------------//
    //                                    RecommendCourseByPriority                                                   //
    //----------------------------------------------------------------------------------------------------------------//
    //                            Retrieve the list of domains for the coupled function                               //
    //----------------------------------------------------------------------------------------------------------------//
    //public String RecommendCourseByPriority(int employee_id) {
//
    //    //empty variables
    //    Init();
//
    //    //Populate List with domains coupled to employee
    //    domainsList = GetDomainsForEmployeeId(employee_id);
//
//
//
    //    //Iterate over domains
    //    for (Domains domain : domainsList) {
    //        //check if a domain is set, then check priority
    //        if (selectedDomain == null || domain.getPriority() > selectedDomain.getPriority()) {
    //            //if a domain is not set or priority is higher, overwrite
    //            selectedDomain = domain;
    //            coursesForDomainList = GetCoursesForDomain(selectedDomain.getId());
    //        }
    //    }
//
    //    //Populate list with courses for selected domain
    //    coursesForDomainList = GetCoursesForDomain(selectedDomain.getId());
    //    counter = 0;
    //    try{
    //        for(Courses course: coursesForDomainList){
    //            if(!checkIfCourseFollowed(employee_id, course.getId())){
    //                checkedCoursesForDomainList.add(counter, course);
    //                counter++;
    //            }
    //        }
    //    }catch (Error e){
    //        return e.getStackTrace().toString();
    //    }
//
    //    //return result
    //    return selectAvailableCourse(checkedCoursesForDomainList);
    //}

    //----------------------------------------------------------------------------------------------------------------//
    //                                         selectAvailableCourse                                                  //
    //----------------------------------------------------------------------------------------------------------------//
    //                            Retrieve the list of domains for the coupled function                               //
    //----------------------------------------------------------------------------------------------------------------//


    //public String selectAvailableCourse(List<Courses> checkedCoursesForDomainList){
    //    //check if there are any courses found
    //    if (checkedCoursesForDomainList.size() > 1) {
    //        //if courses are found, select one at random
    //        int rnd = rand.nextInt(checkedCoursesForDomainList.size());
    //        return checkedCoursesForDomainList.get(rnd).toString();
    //    } else if (checkedCoursesForDomainList.size() == 1) {
    //        //if only one course is found, return that course
    //        return checkedCoursesForDomainList.get(0).toString();
    //    } else {
    //        //if none are found, inform user
    //        return "No courses found";
    //    }
    //}

    //----------------------------------------------------------------------------------------------------------------//
    //                                      CoupledDomainsByFunctionId                                                //
    //----------------------------------------------------------------------------------------------------------------//
    //                            Retrieve the list of domains for the coupled function                               //
    //----------------------------------------------------------------------------------------------------------------//
    private List<Domains> CoupledDomainsByFunctionid(int id){
        // empty variables
        //Init();

        // fill the list with joint objects
        //
        //
        // ############ REFACTORED METHOD, SEE FUNCTIONSDOMAINSREPOSITORY#########
        //
        //
        //
        //functionsDomainsList = functionsDomainsRepository.findAllByFunction_Id(id);

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
    //public boolean checkIfCourseFollowed(int employee_id, int course_id) {
//
    //    //empty variables
    //    //Init();
//
    //    //insert all employeecourses object into an iterable
    //    allEmployeeCoursesIterable = employeeCoursesRepository.findAll();
//
    //    //convert iterable into List object
    //    allEmployeeCoursesIterable.forEach(allEmployeeCoursesList::add);
//
    //    //run through the list to check if there's a record w
    //    // ith both input parameters matching
    //    for (EmployeeCourses employeeCourse : allEmployeeCoursesList) {
    //        if (employeeCourse.getCourse().getId() == course_id && employeeCourse.getEmployee().getId() == employee_id) {
    //            //return result
    //            return true;
    //        }
    //    }
    //    //return result
    //    return false;
    //}

    //----------------------------------------------------------------------------------------------------------------//
    //                                          getCoursesForDomain                                                   //
    //----------------------------------------------------------------------------------------------------------------//
    //                            Retrieve the list of courses for the selected domain                                //
    //----------------------------------------------------------------------------------------------------------------//
    //
    //}

    //----------------------------------------------------------------------------------------------------------------//
    //                                       GetDomainsForEmployeeId                                                  //
    //----------------------------------------------------------------------------------------------------------------//
    //                              Get all possible domains for a single employee                                    //
    //----------------------------------------------------------------------------------------------------------------//
    public List<Domains> GetDomainsForEmployeeId(int id){

        //empty variables
        //Init();

        //get Employee object by inserted id
        Employees selectedEmployee = employeesRepository.findById(id);




        //get Function object from selected Employee object
                                                                    //employeesFunction = selectedEmployee.getFunction();

        //get id from resulted Function object
        Integer functionid = employeesFunction.getId();

        //return result
        return CoupledDomainsByFunctionid(functionid);
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                        AllCoursesByEmployeeId                                                //
    //----------------------------------------------------------------------------------------------------------------//
    //                            Retrieve the list of courses for the selected domain                                //
    //----------------------------------------------------------------------------------------------------------------//

}