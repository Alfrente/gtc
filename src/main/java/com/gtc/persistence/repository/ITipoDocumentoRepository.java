package com.gtc.persistence.repository;

import com.gtc.persistence.entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
}
