package com.gtc.mapper;

import com.gtc.persistence.entity.TipoDocumento;
import com.gtc.service.dto.request.TipoDocumentoInpDto;
import com.gtc.service.dto.response.TipoDocumentoResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITipoDocumentoMapper extends IMapper<TipoDocumento, TipoDocumentoInpDto, TipoDocumentoResDto> {
    @Override
    @Mapping(target = "id", ignore = true)
    TipoDocumento aEntidad(TipoDocumentoInpDto inpDto);
}
