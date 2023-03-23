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

import static com.gtc.util.MensajeError.*;
import static com.gtc.util.MetodoCompartidos.stringALong;
import static com.gtc.util.MetodoCompartidos.validarId;

@Service
@RequiredArgsConstructor
public class DaneService implements ICrud<DaneResDto, DaneInpDto> {
    private final IDaneRepository repository;
    private final IDaneMapper mapper;
    private final TipoDaneService tipoDaneService;

    @Override
    public DaneResDto getById(String id) {
        return mapper.aOutDto(repository.findById(validarId(id)).orElse(null));
    }

    @Override
    public List<DaneResDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    public DaneResDto save(DaneInpDto inpDto) {
        validarTipoDane(inpDto.idTipoDane());
        return mapper.aOutDto(repository.save(mapper.aEntidad(inpDto)));
    }

    @Override
    public void delete(String id) {
        validarDaneBd(validarId(id));
        repository.deleteById(validarId(id));
    }

    public DaneResDto update(String id, DaneInpDto inpDto) {
        return validarCampoModificar(inpDto, validarId(id));
    }

    public Dane validarDaneBd(Long id) {
        if (id == null)
            throw new ExceptionGtc(ID_INVALIDO);

        Optional<Dane> dane = repository.findById(id);
        if (dane.isEmpty())
            throw new ExceptionGtc(EL_ID + id + DANE_NO_DISPONIBLE);

        return dane.get();
    }

    private void validarTipoDane(String idTipoDane) {
        if (idTipoDane != null && tipoDaneService.getById(idTipoDane) == null)
            throw new ExceptionGtc(EL_ID + idTipoDane + TIPO_DANE_NO_DISPONIBLE);
    }

    private DaneResDto validarCampoModificar(DaneInpDto inpDto, Long id) {
        if (inpDto == null)
            throw new ExceptionGtc(INGRESE_INFORMACION_REQUERIDA);

        Dane dane = validarDaneBd(id);
        DaneInpDto db = mapper.aInpDt(dane);
        if (db.equals(inpDto))
            return mapper.aOutDto(dane);

        validarTipoDane(inpDto.idTipoDane());

        dane.setDescripcion(inpDto.descripcion() != null ? inpDto.descripcion() : dane.getDescripcion());
        dane.setIdTipoDane(inpDto.idTipoDane() != null ? stringALong(inpDto.idTipoDane()) : dane.getTipoDane().getId());

        repository.save(dane);

        dane.setTipoDane(tipoDaneService.validarIdBd(dane.getIdTipoDane()));

        return mapper.aOutDto(dane);
    }

}
