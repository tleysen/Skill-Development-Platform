package com.SDP.Repositories;

import com.SDP.Models.Domains;
import com.SDP.Models.Functions;
import com.SDP.Models.FunctionsDomains;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DomainsRepository extends CrudRepository<Domains, Long> {

    Domains findById(int id);
}
