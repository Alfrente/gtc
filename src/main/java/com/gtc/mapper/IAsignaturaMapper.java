package com.gtc.mapper;

import com.gtc.persistence.entity.Asignatura;
import com.gtc.service.dto.request.AsignaturaInpDto;
import com.gtc.service.dto.response.AsignaturaRestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IEstudianteMapper.class, IDocenteMapper.class})
public interface IAsignaturaMapper extends IMapper<Asignatura, AsignaturaInpDto, AsignaturaRestDto>{

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estudiantes", ignore = true)
    @Mapping(target = "docente", ignore = true)
    Asignatura aEntidad(AsignaturaInpDto asignaturaInpDto);

    @Mapping(target = "estudianteResDtos", source = "estudiantes")
    AsignaturaRestDto aOutDto(Asignatura asignatura);
}
