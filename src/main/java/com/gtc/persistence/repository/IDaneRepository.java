package com.gtc.persistence.repository;

import com.gtc.persistence.entity.Dane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDaneRepository extends JpaRepository<Dane, Long> {
}
