package com.gtc.mapper;

import com.gtc.persistence.entity.EstudianteAsignatura;
import com.gtc.service.dto.request.EstudianteAsignaturaInpDto;
import com.gtc.service.dto.response.EstudianteAsignaturaResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IEstudianteAsignaturaMapper extends IMapper<EstudianteAsignatura, EstudianteAsignaturaInpDto, EstudianteAsignaturaResDto> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estudiante", ignore = true)
    @Mapping(target = "asignatura", ignore = true)
    EstudianteAsignatura aEntidad(EstudianteAsignaturaInpDto estudianteAsignaturaInpDto);

    EstudianteAsignaturaResDto aOutDto(EstudianteAsignatura estudianteAsignatura);
}
