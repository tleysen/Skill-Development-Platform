package com.SDP.Repositories;

import com.SDP.Models.Employees;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EmployeesRepository extends CrudRepository<Employees, Long> {
    Employees findById(int id);
}