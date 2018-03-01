package com.SDP.Repositories;

import com.SDP.Models.Functions;
import org.springframework.data.repository.CrudRepository;

public interface FunctionsRepository extends CrudRepository<Functions, Long> {

    Functions findById(int id);

    Functions findByName(String name);
}
