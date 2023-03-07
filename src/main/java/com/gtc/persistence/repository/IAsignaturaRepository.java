package com.gtc.persistence.repository;

import com.gtc.persistence.entity.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAsignaturaRepository extends JpaRepository<Asignatura, Long> {
}
