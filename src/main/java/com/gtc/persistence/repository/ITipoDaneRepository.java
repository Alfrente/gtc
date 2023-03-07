package com.gtc.persistence.repository;

import com.gtc.persistence.entity.TipoDane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoDaneRepository extends JpaRepository<TipoDane, Long> {
}
