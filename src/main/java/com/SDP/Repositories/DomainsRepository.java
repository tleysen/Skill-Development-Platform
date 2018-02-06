package com.SDP.Repositories;

import com.SDP.Models.Domains;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DomainsRepository extends CrudRepository<Domains, Long> {

    List<Domains> findById(int id);

}
