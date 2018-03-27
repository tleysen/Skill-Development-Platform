package com.SDP.Repositories;

import com.SDP.Models.EmployeesFunctions;
import com.SDP.Models.Functions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeesFunctionsRepository extends CrudRepository<EmployeesFunctions, Long> {

    List<EmployeesFunctions> findAllByEmployee_Id(int id);

}
