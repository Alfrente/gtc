package com.gtc.mapper;

import com.gtc.persistence.entity.Estudiante;
import com.gtc.service.dto.SDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EstudianteResponseMapper{

   public SDto aSDto(Estudiante estudiante){
       Set<String> notas=new HashSet<>();
       Set<String> docentes=new HashSet<>();

       estudiante.getEstudianteAsignaturas().forEach(ea -> notas.add(String.valueOf(ea.getNota())));
       estudiante.getAsignaturas().forEach(asignatura -> docentes.add(asignatura.getDocente().getNombres()));

       return new SDto(estudiante.getNombres(), estudiante.getGrado().getDescripcion(), docentes, notas);
    }

    public List<SDto> aSDtoList(List<Estudiante> estudianteList){
        List<SDto> dtoList=new ArrayList<>();
       estudianteList.forEach(estudiante -> dtoList.add(aSDto(estudiante)));
       return dtoList;
    }
}
