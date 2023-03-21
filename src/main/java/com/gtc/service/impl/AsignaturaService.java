package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.IAsignaturaMapper;
import com.gtc.persistence.entity.Asignatura;
import com.gtc.persistence.entity.Docente;
import com.gtc.persistence.repository.IAsignaturaRepository;
import com.gtc.persistence.repository.IDocenteRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.request.AsignaturaInpDto;
import com.gtc.service.dto.response.AsignaturaRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.gtc.util.MetodosEstatico.longValue;

@Service
@RequiredArgsConstructor
public class AsignaturaService implements ICrud<AsignaturaRestDto, AsignaturaInpDto> {

    private final IAsignaturaRepository repository;
    private final IDocenteRepository docenteRepository;
    private final IAsignaturaMapper mapper;
    private final DocenteService docenteService;

    @Override
    public AsignaturaRestDto getById(Long id) {
        return mapper.aOutDto(repository.findById(id).orElse(null));
    }

    @Override
    public List<AsignaturaRestDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    public AsignaturaRestDto save(AsignaturaInpDto inpDto) {
        validaDocente(longValue(inpDto.idDocente()));
        return mapper.aOutDto(repository.save(mapper.aEntidad(inpDto)));
    }

    @Override
    public void delete(Long id) {
        validarAsignaturaIdBd(id);
        repository.deleteById(id);
    }

    public AsignaturaRestDto update(Long id, AsignaturaInpDto inpDto) {
        Asignatura asignatura = validarAsignaturaIdBd(id);
        validarDocenteIdBd(longValue(inpDto.idDocente()));
        validaDocente(longValue(inpDto.idDocente()));
        return mapper.aOutDto(repository.save(validarCampoModificar(inpDto, asignatura)));
    }

    private Asignatura validarAsignaturaIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<com.gtc.persistence.entity.Asignatura> asignatura = repository.findById(id);
        if (asignatura.isEmpty())
            throw new ExceptionGtc("El id " + id + " no esta disponible");

        return asignatura.get();
    }

    private void validarDocenteIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<Docente> docente = docenteRepository.findById(id);
        if (docente.isEmpty())
            throw new ExceptionGtc("El id " + id + " de el docente no esta disponible");
    }

    private Asignatura validarCampoModificar(AsignaturaInpDto inpDto, Asignatura asignatura) {

        if (inpDto == null)
            throw new ExceptionGtc("Ingrese la informacion requerida");

        asignatura.setId(inpDto.idDocente() != null ? longValue(inpDto.idDocente()) : asignatura.getId());
        asignatura.setDescripcion(inpDto.descripcion() != null ? inpDto.descripcion() : asignatura.getDescripcion());

        return asignatura;
    }

    private void validaDocente(Long idDocente) {
        if (idDocente != null && docenteService.getById(idDocente) == null)
            throw new ExceptionGtc("El id docente " + idDocente + " no disponible");
    }

}
