package com.SDP.Repositories;

import com.SDP.Models.Scores;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScoresRepository extends CrudRepository<Scores, Long> {

    List<Scores> findByEmployee_IdOrderByPointsDesc(int id);

    List<Scores> findByEmployee_IdOrderByDateAscIdAsc(int id);

    List<Scores> findByEmployee_IdAndDomain_IdOrderByDateDescIdDesc(int employeeId, int domainId);

    List<Scores> findByEmployee_IdOrderByDateDescIdDesc(int id);

    List<Scores> findAllByEmployee_IdAndDomain_Id(int employeeId, int domainId);
}
