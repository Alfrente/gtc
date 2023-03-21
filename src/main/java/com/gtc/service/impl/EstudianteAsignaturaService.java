package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.IEstudianteAsignaturaMapper;
import com.gtc.persistence.entity.EstudianteAsignatura;
import com.gtc.persistence.repository.IEstudianteAsignaturaRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.request.EstudianteAsignaturaInpDto;
import com.gtc.service.dto.response.EstudianteAsignaturaResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.gtc.util.MetodosEstatico.*;

@Service
@RequiredArgsConstructor
public class EstudianteAsignaturaService implements ICrud<EstudianteAsignaturaResDto, EstudianteAsignaturaInpDto> {

    private final IEstudianteAsignaturaRepository repository;
    private final IEstudianteAsignaturaMapper mapper;
    private final AsignaturaService asignaturaService;
    private final EstudianteService estudianteService;


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
        validarAsignatura(longValue(inpDto.idAsignatura()));
        validarEstudiante(longValue(inpDto.idEstudiante()));
        return mapper.aOutDto(repository.save(mapper.aEntidad(inpDto)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    public EstudianteAsignaturaResDto update(Long id, EstudianteAsignaturaInpDto inpDto) {
        EstudianteAsignatura estudianteAsignatura = validarEstudianteAsignaturaIdBd(id);
        validarAsignatura(longValue(inpDto.idAsignatura()));
        validarEstudiante(longValue(inpDto.idEstudiante()));
        return mapper.aOutDto(repository.save(validarCampoModificar(inpDto, estudianteAsignatura)));
    }

    private EstudianteAsignatura validarCampoModificar(EstudianteAsignaturaInpDto inpDto,
                                                       EstudianteAsignatura estudianteAsignatura) {

        if (inpDto == null)
            throw new ExceptionGtc("Ingrese la informacion requerida");

        estudianteAsignatura.setNota(inpDto.nota() != null ? doubleValue(inpDto.nota()) : estudianteAsignatura.getNota());
        estudianteAsignatura.setPeriodo(inpDto.periodo() != null ? integerValue(inpDto.periodo()) : estudianteAsignatura.getPeriodo());
        estudianteAsignatura.setIdAsignatura(inpDto.idAsignatura() != null ? longValue(inpDto.idAsignatura()) : estudianteAsignatura.getIdAsignatura());
        estudianteAsignatura.setIdEstudiante(inpDto.idEstudiante() != null ? longValue(inpDto.idEstudiante()) : estudianteAsignatura.getIdEstudiante());

        return estudianteAsignatura;
    }

    private EstudianteAsignatura validarEstudianteAsignaturaIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<EstudianteAsignatura> estudianteAsignatura = repository.findById(id);
        if (estudianteAsignatura.isEmpty())
            throw new ExceptionGtc("El id " + id + " no esta disponible");

        return estudianteAsignatura.get();
    }


    private void validarAsignatura(Long idAsignatura) {
        if (idAsignatura != null && asignaturaService.getById(idAsignatura) == null)
            throw new ExceptionGtc("El id grado " + idAsignatura + " no disponible");
    }

    private void validarEstudiante(Long idEstudiante) {
        if (idEstudiante != null && estudianteService.getById(idEstudiante) == null)
            throw new ExceptionGtc("El id asignatura " + idEstudiante + " no disponible");
    }
}
