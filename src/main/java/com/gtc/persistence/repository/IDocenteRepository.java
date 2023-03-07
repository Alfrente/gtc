package com.gtc.persistence.repository;

import com.gtc.persistence.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocenteRepository extends JpaRepository<Docente, Long> {
}
