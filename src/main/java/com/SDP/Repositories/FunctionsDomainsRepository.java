package com.SDP.Repositories;

import com.SDP.Models.Functions;
import com.SDP.Models.FunctionsDomains;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FunctionsDomainsRepository extends CrudRepository<FunctionsDomains, Long> {


    List<FunctionsDomains> findAllByFunction_Id(int id);
}
