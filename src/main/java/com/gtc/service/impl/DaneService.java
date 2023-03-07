package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.IDaneMapper;
import com.gtc.persistence.entity.Dane;
import com.gtc.persistence.repository.IDaneRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.request.DaneInpDto;
import com.gtc.service.dto.response.DaneResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DaneService implements ICrud<DaneResDto, DaneInpDto> {
    private final IDaneRepository repository;
    private final IDaneMapper mapper;

    @Override
    public DaneResDto getById(Long id) {
        return mapper.aOutDto(repository.findById(id).orElse(null));
    }

    @Override
    public List<DaneResDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    public DaneResDto save(DaneInpDto inpDto) { //
        return mapper.aOutDto(repository.save(mapper.aEntidad(inpDto)));
    }

    @Override
    public void delete(Long id) {
        validarDocenteIdBd(id);
        repository.deleteById(id);
    }

    @Override
    public DaneResDto update(Long id, DaneInpDto inpDto) {
        Dane dane = validarDocenteIdBd(id);
        return mapper.aOutDto(repository.save(validarCampoModificar(inpDto, dane)));
    }

    private Dane validarDocenteIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<Dane> dane = repository.findById(id);
        if (dane.isEmpty())
            throw new ExceptionGtc("El id " + id + " no esta disponible");

        return dane.get();
    }

    private Dane validarCampoModificar(DaneInpDto inpDto, Dane dane) {

        if (inpDto == null)
            throw new ExceptionGtc("Ingrese la informacion requerida");

        dane.setDescripcion(inpDto.getDescripcion() != null ? inpDto.getDescripcion() : dane.getDescripcion());
        dane.getTipoDane().setId(inpDto.getIdTipoDane() != null ? longValue(inpDto.getIdTipoDane()) : dane.getTipoDane().getId());

        return dane;
    }

    private Long longValue(String valor) {
        return Long.valueOf(valor);
    }
}
