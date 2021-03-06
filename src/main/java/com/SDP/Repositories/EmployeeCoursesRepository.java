package com.SDP.Repositories;

import com.SDP.Models.EmployeeCourses;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeCoursesRepository extends CrudRepository<EmployeeCourses, Long> {

    List<EmployeeCourses> findAllByEmployee_Id(int id);

    EmployeeCourses findByEmployee_IdAndCourse_Id(int emp_id, int c_id);


}
