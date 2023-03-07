package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.ITipoDocumentoMapper;
import com.gtc.persistence.entity.TipoDocumento;
import com.gtc.persistence.repository.ITipoDocumentoRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.request.TipoDocumentoInpDto;
import com.gtc.service.dto.response.TipoDocumentoResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoDocumentoService implements ICrud<TipoDocumentoResDto, TipoDocumentoInpDto> {

    private final ITipoDocumentoRepository repository;
    private final ITipoDocumentoMapper mapper;

    @Override
    public TipoDocumentoResDto getById(Long id) {
        return mapper.aOutDto(repository.findById(id).orElse(null));
    }

    @Override
    public List<TipoDocumentoResDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    public TipoDocumentoResDto save(TipoDocumentoInpDto inpDto) {
        return mapper.aOutDto(repository.save(mapper.aEntidad(inpDto)));
    }

    @Override
    public void delete(Long id) {
        validarIdBd(id);
        repository.deleteById(id);
    }

    @Override
    public TipoDocumentoResDto update(Long id, TipoDocumentoInpDto inpDto) {
        TipoDocumento tipoDocumento = validarIdBd(id);
        return mapper.aOutDto(repository.save(validarCampoModificar(inpDto, tipoDocumento)));
    }

    private TipoDocumento validarCampoModificar(TipoDocumentoInpDto inpDto, TipoDocumento tipoDocumento) {

        if (inpDto == null)
            throw new ExceptionGtc("Ingrese la informacion requerida");

        tipoDocumento.setDescripcion(inpDto.getDescripcion() != null ? inpDto.getDescripcion() : tipoDocumento.getDescripcion());

        return tipoDocumento;
    }

    private TipoDocumento validarIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<TipoDocumento> tipoDocumento = repository.findById(id);
        if (tipoDocumento.isEmpty())
            throw new ExceptionGtc("El id " + id + " no esta disponible");

        return tipoDocumento.get();
    }
}
