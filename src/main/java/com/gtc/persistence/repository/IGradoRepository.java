package com.gtc.persistence.repository;

import com.gtc.persistence.entity.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGradoRepository extends JpaRepository<Grado, Long> {
}
