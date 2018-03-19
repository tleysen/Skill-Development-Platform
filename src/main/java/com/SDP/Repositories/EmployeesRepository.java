package com.SDP.Repositories;

import com.SDP.Models.Employees;
import org.springframework.data.repository.CrudRepository;




public interface EmployeesRepository extends CrudRepository<Employees, Long> {
    Employees findById(int id);

}