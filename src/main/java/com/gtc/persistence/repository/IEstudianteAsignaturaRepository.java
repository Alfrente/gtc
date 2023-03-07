package com.gtc.persistence.repository;

import com.gtc.persistence.entity.EstudianteAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstudianteAsignaturaRepository extends JpaRepository<EstudianteAsignatura, Long> {
}
