package com.gtc.mapper;

import com.gtc.persistence.entity.Estudiante;
import com.gtc.service.dto.request.EstudianteInpDto;
import com.gtc.service.dto.request.update.EstudianteInpUpdDto;
import com.gtc.service.dto.response.EstudianteResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IEstudianteConRelacionMapper extends IMapper<Estudiante, EstudianteInpDto, EstudianteResDto> {

    @Mapping(target = "estudianteAsignaturas", ignore = true)
    @Mapping(target = "tipoDocumento", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "grado", ignore = true)
    @Mapping(target = "dane", ignore = true)
    @Mapping(target = "asignaturas", ignore = true)
    Estudiante aEntidad(EstudianteInpDto estudianteInpDto);

    @Mapping(target = "asignaturaResDtoList", source = "asignaturas")
    @Mapping(target = "tipoDocumentoResDto", source = "tipoDocumento")
    @Mapping(target = "gradoResDto", source = "grado")
    @Mapping(target = "daneResDto", source = "dane")
    EstudianteResDto aOutDto(Estudiante estudiante);

    EstudianteInpUpdDto aInpUpdDto(Estudiante estudiante);

}
