package com.gtc.mapper;

import com.gtc.persistence.entity.Grado;
import com.gtc.service.dto.request.GradoInpDto;
import com.gtc.service.dto.response.GradoResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IGradoMapper extends IMapper<Grado, GradoInpDto, GradoResDto>{

    @Mapping(target = "id", ignore = true)
    @Override
    Grado aEntidad(GradoInpDto gradoInpDto);

}
