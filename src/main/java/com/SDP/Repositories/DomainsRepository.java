package com.SDP.Repositories;

import com.SDP.Models.Domains;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface DomainsRepository extends CrudRepository<Domains, Long> {

    Domains findById(int id);

    @Transactional
    void deleteById(int id);

    Domains findByName(String name);
}
