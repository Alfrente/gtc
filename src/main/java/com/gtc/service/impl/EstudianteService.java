package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.IEstudianteConRelacionMapper;
import com.gtc.persistence.entity.Estudiante;
import com.gtc.persistence.repository.IEstudianteRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.SDto;
import com.gtc.service.dto.request.EstudianteInpDto;
import com.gtc.service.dto.response.EstudianteResDto;
import com.gtc.service.dto.request.update.EstudianteInpUpdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.gtc.util.MetodosEstatico.validarFecha;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstudianteService implements ICrud<EstudianteResDto, EstudianteInpDto> {

    private final IEstudianteRepository repository;
    private final IEstudianteConRelacionMapper mapper;

    @Override
    public EstudianteResDto getById(Long id) {
        return mapper.aOutDto(repository.findById(id).orElse(null));
    }

    @Override
    public List<EstudianteResDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    @Transactional
    public EstudianteResDto save(EstudianteInpDto estudianteDto) {
        return mapper.aOutDto(repository.save(mapper.aEntidad(estudianteDto)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarIdBd(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public EstudianteResDto update(Long id, EstudianteInpDto estudianteDto) {
        Estudiante estudianteDb = validarIdBd(id);
        return mapper.aOutDto(repository.save(validarCampoModificar(estudianteDto, estudianteDb)));
    }


    @Transactional
    public EstudianteResDto update(Long id, EstudianteInpUpdDto estudianteDto) {
        Estudiante estudianteDb = validarIdBd(id);
        return mapper.aOutDto(repository.save(validarCampoModificar(estudianteDto, estudianteDb)));
    }

    private Estudiante validarIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<com.gtc.persistence.entity.Estudiante> estudiante = repository.findById(id);
        if (estudiante.isEmpty())
            throw new ExceptionGtc("El id " + id + " no esta disponible");

        return estudiante.get();
    }

    private Estudiante validarCampoModificar(Object dto, Estudiante estudianteDb) {

        if (dto == null)
            throw new ExceptionGtc("Ingrese la informacion requerida");

        EstudianteInpDto dato = (EstudianteInpDto) dto;

        if (validarFecha(dato.getFechaNacimiento()))
            throw new ExceptionGtc("La fecha ingresada es incorrecta");

        estudianteDb.setNumeroDocumento(dato.getNumeroDocumento() != null ? longValue(dato.getNumeroDocumento()) :
                estudianteDb.getNumeroDocumento());
        estudianteDb.setNombres(dato.getNombres() != null ? dato.getNombres() : estudianteDb.getNombres());
        estudianteDb.setApellidos(dato.getApellidos() != null ? dato.getApellidos() : estudianteDb.getApellidos());
        estudianteDb.setFechaNacimiento(dato.getFechaNacimiento() != null ? LocalDate.parse(dato.getFechaNacimiento()) :
                estudianteDb.getFechaNacimiento());
        estudianteDb.setDireccion(dato.getDireccion() != null ? dato.getDireccion() : estudianteDb.getDireccion());
        estudianteDb.setEmail(dato.getEmail() != null ? dato.getEmail() : estudianteDb.getEmail());
        estudianteDb.setFijo(dato.getFijo() != null ? longValue(dato.getFijo()) : estudianteDb.getFijo());
        estudianteDb.setCelular(dato.getCelular() != null ? longValue(dato.getCelular()) : estudianteDb.getCelular());
        estudianteDb.getGrado().setId(dato.getIdGrado() != null ? longValue(dato.getIdGrado()) :
                estudianteDb.getGrado().getId());
        estudianteDb.getTipoDocumento().setId(dato.getIdGrado() != null ? longValue(dato.getIdGrado()) :
                estudianteDb.getTipoDocumento().getId());

        return estudianteDb;
    }


    public List<SDto> getEstudiantesJoinNota() {
        //return repository.estudiantesJoinNota();
        return null;
    }

    private Long longValue(String valor) {
        return Long.valueOf(valor);
    }
}
