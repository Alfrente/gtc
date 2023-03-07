package com.gtc.mapper;

import com.gtc.persistence.entity.Docente;
import com.gtc.service.dto.request.DocenteInpDto;
import com.gtc.service.dto.response.sin_otra_clase.DocenteResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IDocenteMapper extends IMapper<Docente, DocenteInpDto, DocenteResDto>{

    @Override
    @Mapping(target = "tipoDocumento", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "grado", ignore = true)
    @Mapping(target = "asignaturaList", ignore = true)
    Docente aEntidad(DocenteInpDto docenteInpDto);
}
