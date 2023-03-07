package com.gtc.mapper;

import com.gtc.persistence.entity.EstudianteAsignatura;
import com.gtc.service.dto.request.EstudianteAsignaturaInpDto;
import com.gtc.service.dto.response.EstudianteAsignaturaResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IEstudianteAsignaturaMapper extends IMapper<EstudianteAsignatura, EstudianteAsignaturaInpDto, EstudianteAsignaturaResDto>{

    @Override
    @Mapping(target = "estudianteAsignaturaFk", ignore = true)
    EstudianteAsignatura aEntidad(EstudianteAsignaturaInpDto estudianteAsignaturaInpDto);


    @Mapping(target = "estudianteAsignaturaFkDto", source = "estudianteAsignaturaFk")
    EstudianteAsignaturaResDto aOutDto(EstudianteAsignatura estudianteAsignatura);
}
