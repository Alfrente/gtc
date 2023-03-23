package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.IAsignaturaMapper;
import com.gtc.persistence.entity.Asignatura;
import com.gtc.persistence.entity.Docente;
import com.gtc.persistence.repository.IAsignaturaRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.request.AsignaturaInpDto;
import com.gtc.service.dto.response.AsignaturaRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.gtc.util.MensajeError.*;
import static com.gtc.util.MetodoCompartidos.stringALong;
import static com.gtc.util.MetodoCompartidos.validarId;

@Service
@RequiredArgsConstructor
public class AsignaturaService implements ICrud<AsignaturaRestDto, AsignaturaInpDto> {

    private final IAsignaturaRepository repository;
    private final IAsignaturaMapper mapper;
    private final DocenteService docenteService;

    @Override
    public AsignaturaRestDto getById(String id) {
        return mapper.aOutDto(repository.findById(validarId(id)).orElse(null));
    }

    @Override
    public List<AsignaturaRestDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    public AsignaturaRestDto save(AsignaturaInpDto inpDto) {
        validaDocente(inpDto.idDocente());
        return mapper.aOutDto(repository.save(mapper.aEntidad(inpDto)));
    }

    @Override
    public void delete(String id) {
        validarAsignaturaBd(validarId(id));
        repository.deleteById(validarId(id));
    }

    public AsignaturaRestDto update(String id, AsignaturaInpDto inpDto) {
        return validarCampoModificar(inpDto, id);
    }

    private void validaDocente(String idDocente) {
        if (idDocente != null && docenteService.getById(idDocente) == null)
            throw new ExceptionGtc(EL_ID + idDocente + DOCENTE_NO_DISPONIBLE);
    }

    private Asignatura validarAsignaturaBd(Long id) {
        if (id == null) throw new ExceptionGtc(ID_INVALIDO);

        Optional<com.gtc.persistence.entity.Asignatura> asignatura = repository.findById(id);
        if (asignatura.isEmpty()) throw new ExceptionGtc(EL_ID + id + ASIGNATURA_NO_DISPONIBLE);

        return asignatura.get();
    }

    private AsignaturaRestDto validarCampoModificar(AsignaturaInpDto inpDto, String id) {

        if (inpDto == null) throw new ExceptionGtc(INGRESE_INFORMACION_REQUERIDA);

        Asignatura asignatura = validarAsignaturaBd(validarId(id));
        AsignaturaInpDto db = mapper.aInpDto(asignatura);
        if (db.equals(inpDto)) return mapper.aOutDto(asignatura);

        Docente docente = docenteService.validarDocenteIdBd(stringALong(inpDto.idDocente()));

        asignatura.setIdDocente(inpDto.idDocente() != null ? stringALong(inpDto.idDocente()) : asignatura.getId());
        asignatura.setDescripcion(inpDto.descripcion() != null ? inpDto.descripcion() : asignatura.getDescripcion());

        repository.save(asignatura);

        asignatura.setDocente(docente);

        return mapper.aOutDto(asignatura);
    }

}
