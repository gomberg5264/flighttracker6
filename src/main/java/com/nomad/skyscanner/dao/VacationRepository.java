package com.nomad.skyscanner.dao;

import com.nomad.skyscanner.model.Vacation;
import org.springframework.data.repository.CrudRepository;

public interface VacationRepository extends CrudRepository<Vacation, Long> {
}
