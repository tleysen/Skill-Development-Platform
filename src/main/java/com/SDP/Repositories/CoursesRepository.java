package com.SDP.Repositories;

import com.SDP.Models.Courses;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursesRepository extends CrudRepository<Courses, Long> {


    List<Courses> findAllByDomain_Id(int id);

    List<Courses> findById(int id);
}
