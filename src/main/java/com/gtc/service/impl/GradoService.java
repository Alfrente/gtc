package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.IGradoMapper;
import com.gtc.persistence.entity.Grado;
import com.gtc.persistence.repository.IGradoRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.request.GradoInpDto;
import com.gtc.service.dto.response.GradoResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.gtc.util.MensajeError.*;
import static com.gtc.util.MetodoCompartidos.validarId;

@Service
@RequiredArgsConstructor
public class GradoService implements ICrud<GradoResDto, GradoInpDto> {
    private final IGradoRepository repository;
    private final IGradoMapper mapper;

    @Override
    public GradoResDto getById(String id) {
        return mapper.aOutDto(repository.findById(validarId(id)).orElse(null));
    }

    @Override
    public List<GradoResDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    public GradoResDto save(GradoInpDto gradoInpDto) {
        return mapper.aOutDto(repository.save(mapper.aEntidad(gradoInpDto)));
    }

    @Override
    public void delete(String id) {
        validarGradoBd(validarId(id));
        repository.deleteById(validarId(id));
    }

    public GradoResDto update(String id, String descripcion) {
        Grado grado = validarGradoBd(validarId(id));
        grado.setDescripcion(descripcion);
        return mapper.aOutDto(repository.save(grado));
    }

    public Grado validarGradoBd(Long id) {
        if (id == null)
            throw new ExceptionGtc(ID_INVALIDO);

        Optional<Grado> grado = repository.findById(id);
        if (grado.isEmpty())
            throw new ExceptionGtc(EL_ID+ id + GRADO_NO_DISPONIBLE);

        return grado.get();
    }

}
