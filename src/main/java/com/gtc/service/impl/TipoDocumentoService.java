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

import static com.gtc.util.MensajeError.*;
import static com.gtc.util.MetodoCompartidos.validarId;

@Service
@RequiredArgsConstructor
public class TipoDocumentoService implements ICrud<TipoDocumentoResDto, TipoDocumentoInpDto> {

    private final ITipoDocumentoRepository repository;
    private final ITipoDocumentoMapper mapper;

    @Override
    public TipoDocumentoResDto getById(String id) {
        return mapper.aOutDto(repository.findById(validarId(id)).orElse(null));
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
    public void delete(String id) {
        validarTipoDocumentoBd(validarId(id));
        repository.deleteById(validarId(id));
    }

    public TipoDocumentoResDto update(String id, String nuevoTipoDocumento) {
        TipoDocumento tipoDocumento = validarTipoDocumentoBd(validarId(id));
        tipoDocumento.setDescripcion(nuevoTipoDocumento);
        return mapper.aOutDto(repository.save(tipoDocumento));
    }

    public TipoDocumento validarTipoDocumentoBd(Long id) {
        if (id == null)
            throw new ExceptionGtc(ID_INVALIDO);

        Optional<TipoDocumento> tipoDocumento = repository.findById(id);
        if (tipoDocumento.isEmpty())
            throw new ExceptionGtc(EL_ID + id + TIPO_DOCUMENTO_NO_DISPONIBLE);

        return tipoDocumento.get();
    }

}
