package com.gtc.persistence.repository;

import com.gtc.persistence.entity.Estudiante;
import com.gtc.service.dto.SDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {

    /*@Query(name = """
            SELECT e.nombres as nombreEstudiante, ea.nota as notaEstudiante,
            g.descripcion as nombreCurso, d.nombres as nombreDocente
            FROM estudiante e join estudiante_asignatura ea on ea.id_asignatura = e.id
            join grado g on e.id=g.id join docente d on g.id=d.id_grado_responsable
            where ea.nota > 3.1 order by ea.nota asc
            """, nativeQuery = true)*/
    //List<SDto> estudiantesJoinNota();
}
