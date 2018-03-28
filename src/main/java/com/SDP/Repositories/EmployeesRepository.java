package com.SDP.Repositories;

import com.SDP.Models.Employees;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


public interface EmployeesRepository extends CrudRepository<Employees, Long> {
    Employees findById(int id);

    @Transactional
    void deleteById(int id);

}