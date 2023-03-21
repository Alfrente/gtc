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

@Service
@RequiredArgsConstructor
public class GradoService implements ICrud<GradoResDto, GradoInpDto> {
    private final IGradoRepository repository;
    private final IGradoMapper mapper;

    @Override
    public GradoResDto getById(Long id) {
        return mapper.aOutDto(repository.findById(id).orElse(null));
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
    public void delete(Long id) {
        validarIdBd(id);
        repository.deleteById(id);
    }

    public GradoResDto update(Long id, String nuevoTipoDane) {
        Grado grado = validarIdBd(id);
        grado.setDescripcion(nuevoTipoDane);
        return mapper.aOutDto(repository.save(grado));
    }



    private Grado validarIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<Grado> grado = repository.findById(id);
        if (grado.isEmpty())
            throw new ExceptionGtc("El id " + id + " no esta disponible");

        return grado.get();
    }

}
