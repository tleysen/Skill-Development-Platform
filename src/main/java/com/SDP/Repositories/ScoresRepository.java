package com.SDP.Repositories;

import com.SDP.Models.Scores;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScoresRepository extends CrudRepository<Scores, Long> {

    List<Scores> findByEmployee_IdOrderByPointsDesc(int id);

    List<Scores> findByEmployee_IdOrderByDomainAscDateAsc(int id);

    List<Scores> findByEmployee_IdAndDomain_IdOrderByDateDesc(int employeeId, int domainId);
}
