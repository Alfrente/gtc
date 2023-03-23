package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.ITipoDaneMapper;
import com.gtc.persistence.entity.TipoDane;
import com.gtc.persistence.repository.ITipoDaneRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.request.TipoDaneInpDto;
import com.gtc.service.dto.response.TipoDaneResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.gtc.util.MensajeError.*;
import static com.gtc.util.MetodoCompartidos.validarId;

@Service
@RequiredArgsConstructor
public class TipoDaneService implements ICrud<TipoDaneResDto, TipoDaneInpDto> {

    private final ITipoDaneRepository repository;
    private final ITipoDaneMapper mapper;

    @Override
    public TipoDaneResDto getById(String id) {
        return mapper.aOutDto(repository.findById(validarId(id)).orElse(null));
    }

    @Override
    public List<TipoDaneResDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    public TipoDaneResDto save(TipoDaneInpDto tipoDocumentoInpDto) {
        return mapper.aOutDto(repository.save(mapper.aEntidad(tipoDocumentoInpDto)));
    }

    @Override
    public void delete(String id) {
        validarIdBd(validarId(id));
        repository.deleteById(validarId(id));
    }

    public TipoDaneResDto update(String id, String nuevoTipoDane) {
        TipoDane tipoDane = validarIdBd(validarId(id));
        tipoDane.setDescripcion(nuevoTipoDane);
        return mapper.aOutDto(tipoDane);
    }

    public TipoDane validarIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc(ID_INVALIDO);

        Optional<TipoDane> tipoDane = repository.findById(id);
        if (tipoDane.isEmpty())
            throw new ExceptionGtc(EL_ID + id + TIPO_DANE_NO_DISPONIBLE);

        return tipoDane.get();
    }

}
