package com.gtc.mapper;

import com.gtc.persistence.entity.Estudiante;
import com.gtc.service.dto.EstudiantePerRespDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EstudianteResponseMapper{

   public EstudiantePerRespDto aEstudiantePer(Estudiante estudiante){
       Set<String> notas=new HashSet<>();
       Set<String> docentes=new HashSet<>();

       estudiante.getEstudianteAsignaturas().forEach(ea -> notas.add(String.valueOf(ea.getNota())));
       estudiante.getAsignaturas().forEach(asignatura -> docentes.add(asignatura.getDocente().getNombres()));

       return new EstudiantePerRespDto(estudiante.getNombres(), estudiante.getGrado().getDescripcion(), docentes, notas);
    }

    public List<EstudiantePerRespDto> aEstudiantePerList(List<Estudiante> estudianteList){
        List<EstudiantePerRespDto> dtoList=new ArrayList<>();
       estudianteList.forEach(estudiante -> dtoList.add(aEstudiantePer(estudiante)));
       return dtoList;
    }
}
