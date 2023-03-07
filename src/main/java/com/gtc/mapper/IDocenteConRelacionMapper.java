package com.gtc.mapper;

import com.gtc.persistence.entity.Docente;
import com.gtc.service.dto.request.DocenteInpDto;
import com.gtc.service.dto.response.DocenteResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IDescripcionMapper.class, ITipoDaneMapper.class, IGradoMapper.class})
public interface IDocenteConRelacionMapper extends IMapper<Docente, DocenteInpDto, DocenteResDto> {

    @Override
    @Mapping(target = "tipoDocumento", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "grado", ignore = true)
    @Mapping(target = "asignaturaList", ignore = true)
    Docente aEntidad(DocenteInpDto dto);

    @Override
    @Mapping(target = "tipoDocumentoResDto", source = "tipoDocumento")
    @Mapping(target = "gradoResDto", source = "grado")
    @Mapping(target = "asignaturaOutDtoList", source = "asignaturaList")
    DocenteResDto aOutDto(Docente docente);
}
