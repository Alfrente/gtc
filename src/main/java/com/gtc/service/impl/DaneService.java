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

import static com.gtc.util.MetodosEstatico.longValue;

@Service
@RequiredArgsConstructor
public class DaneService implements ICrud<DaneResDto, DaneInpDto> {
    private final IDaneRepository repository;
    private final IDaneMapper mapper;
    private final TipoDaneService tipoDaneService;

    @Override
    public DaneResDto getById(Long id) {
        return mapper.aOutDto(repository.findById(id).orElse(null));
    }

    @Override
    public List<DaneResDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    public DaneResDto save(DaneInpDto inpDto) {
        validarTipoDane(longValue(inpDto.idTipoDane()));
        return mapper.aOutDto(repository.save(mapper.aEntidad(inpDto)));
    }

    @Override
    public void delete(Long id) {
        validarDaneBd(id);
        repository.deleteById(id);
    }

    public DaneResDto update(Long id, DaneInpDto inpDto) {
        return validarCampoModificar(inpDto, id);
    }

    private Dane validarDaneBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<Dane> dane = repository.findById(id);
        if (dane.isEmpty())
            throw new ExceptionGtc("El id " + id + " no esta disponible");

        return dane.get();
    }

    private DaneResDto validarCampoModificar(DaneInpDto inpDto, Long id) {
        if (inpDto == null)
            throw new ExceptionGtc("Ingrese la informacion requerida");

        Dane dane = validarDaneBd(id);

        DaneInpDto modificado = new DaneInpDto(dane.getDescripcion(), String.valueOf(dane.getIdTipoDane()));
        if (modificado.equals(inpDto))
            return mapper.aOutDto(dane);

        validarTipoDane(longValue(inpDto.idTipoDane()));

        dane.setDescripcion(inpDto.descripcion() != null ? inpDto.descripcion() : dane.getDescripcion());
        dane.setIdTipoDane(inpDto.idTipoDane() != null ? longValue(inpDto.idTipoDane()) : dane.getTipoDane().getId());

        repository.save(dane);

        dane.setTipoDane(tipoDaneService.validarIdBd(dane.getIdTipoDane()));

        return mapper.aOutDto(dane);
    }

    private void validarTipoDane(Long idTipoDane) {
        if (idTipoDane != null && tipoDaneService.getById(idTipoDane) == null)
            throw new ExceptionGtc("El id tipo dane " + idTipoDane + " no disponible");
    }

}
