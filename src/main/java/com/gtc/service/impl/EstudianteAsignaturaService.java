package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.IEstudianteAsignaturaMapper;
import com.gtc.persistence.entity.EstudianteAsignatura;
import com.gtc.persistence.entity.EstudianteAsignaturaFk;
import com.gtc.persistence.repository.IEstudianteAsignaturaRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.request.EstudianteAsignaturaInpDto;
import com.gtc.service.dto.response.EstudianteAsignaturaResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstudianteAsignaturaService implements ICrud<EstudianteAsignaturaResDto, EstudianteAsignaturaInpDto> {

    private final IEstudianteAsignaturaRepository repository;
    private final IEstudianteAsignaturaMapper mapper;

    @Override
    public EstudianteAsignaturaResDto getById(Long id) {
        return mapper.aOutDto(repository.findById(id).orElse(null));
    }

    @Override
    public List<EstudianteAsignaturaResDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    public EstudianteAsignaturaResDto save(EstudianteAsignaturaInpDto inpDto) {
        return mapper.aOutDto(repository.save(mapper.aEntidad(inpDto)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public EstudianteAsignaturaResDto update(Long id, EstudianteAsignaturaInpDto estudianteAsignaturaInpDto) {
        EstudianteAsignatura estudianteAsignatura = validarEstudianteAsignaturaIdBd(id);
        return mapper.aOutDto(repository.save(validarCampoModificar(estudianteAsignaturaInpDto, estudianteAsignatura)));
    }

    private EstudianteAsignatura validarCampoModificar(EstudianteAsignaturaInpDto inpDto, EstudianteAsignatura estudianteAsignatura) {

        if (inpDto == null)
            throw new ExceptionGtc("Ingrese la informacion requerida");

        estudianteAsignatura.setNota(inpDto.getNota() != null ? doubleValue(inpDto.getNota()) : estudianteAsignatura.getNota());
        estudianteAsignatura.setPeriodo(inpDto.getPeriodo() != null ? integerValue(inpDto.getPeriodo()) : estudianteAsignatura.getPeriodo());

        if (inpDto.getIdAsignatura() != null && inpDto.getIdEstudiante() != null) {
            estudianteAsignatura.setEstudianteAsignaturaFk(new EstudianteAsignaturaFk(longValue(inpDto.getIdEstudiante()),
                    longValue(inpDto.getIdAsignatura())));
        }

        return estudianteAsignatura;
    }

    private Long longValue(String valor) {
        return Long.valueOf(valor);
    }

    private Double doubleValue(String valor) {
        return Double.parseDouble(valor);
    }

    private Integer integerValue(String valor) {
        return Integer.parseInt(valor);
    }

    private EstudianteAsignatura validarEstudianteAsignaturaIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<EstudianteAsignatura> estudianteAsignatura = repository.findById(id);
        if (estudianteAsignatura.isEmpty())
            throw new ExceptionGtc("El id " + id + " no esta disponible");

        return estudianteAsignatura.get();
    }
}
