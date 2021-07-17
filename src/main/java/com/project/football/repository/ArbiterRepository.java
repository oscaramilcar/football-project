package com.project.football.repository;

import com.project.football.model.Arbiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbiterRepository extends JpaRepository<Arbiter, Long> {
}
