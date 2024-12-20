package com.judo_wrestling_board.repository;

import com.judo_wrestling_board.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    Athlete findByName(String name);
}
