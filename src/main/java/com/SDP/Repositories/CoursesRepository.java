package com.SDP.Repositories;

import com.SDP.Models.Courses;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CoursesRepository extends CrudRepository<Courses, Long> {


    List<Courses> findAllByDomain_Id(int id);

    Courses findById(int id);

    @Transactional
    void deleteById(int id);
}
