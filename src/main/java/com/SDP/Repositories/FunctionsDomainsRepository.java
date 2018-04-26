package com.SDP.Repositories;

import com.SDP.Models.FunctionsDomains;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FunctionsDomainsRepository extends CrudRepository<FunctionsDomains, Long> {


    List<FunctionsDomains> findAllByFunction_IdOrderByDomainPriorityDesc(int id);

    List<FunctionsDomains> findAllByFunction_Name(String name);

    List<FunctionsDomains> findAllByFunction_Id(int id);

    FunctionsDomains findByFunction_IdAndDomain_Name(int id, String name);
}
