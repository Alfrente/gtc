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

import static com.gtc.util.MensajeError.*;
import static com.gtc.util.MetodoCompartidos.*;

@Service
@RequiredArgsConstructor
public class EstudianteAsignaturaService implements ICrud<EstudianteAsignaturaResDto, EstudianteAsignaturaInpDto> {

    private final IEstudianteAsignaturaRepository repository;
    private final IEstudianteAsignaturaMapper mapper;
    private final AsignaturaService asignaturaService;
    private final EstudianteService estudianteService;


    @Override
    public EstudianteAsignaturaResDto getById(String id) {
        return mapper.aOutDto(repository.findById(validarId(id)).orElse(null));
    }

    @Override
    public List<EstudianteAsignaturaResDto> getAll() {
        return mapper.aOutDtoList(repository.findAll());
    }

    @Override
    public EstudianteAsignaturaResDto save(EstudianteAsignaturaInpDto inpDto) {
        validarAsignatura(inpDto.idAsignatura());
        validarEstudiante(inpDto.idEstudiante());
        return mapper.aOutDto(repository.save(mapper.aEntidad(inpDto)));
    }

    @Override
    public void delete(String id) {
        validarEstudianteAsignaturaBd(validarId(id));
        repository.deleteById(validarId(id));
    }


    public EstudianteAsignaturaResDto update(String id, EstudianteAsignaturaInpDto inpDto) {
        return validarCampoModificar(inpDto, id);
    }

    private EstudianteAsignatura validarEstudianteAsignaturaBd(Long id) {
        if (id == null)
            throw new ExceptionGtc(ID_INVALIDO);

        Optional<EstudianteAsignatura> estudianteAsignatura = repository.findById(id);
        if (estudianteAsignatura.isEmpty())
            throw new ExceptionGtc(EL_ID + id + ESTUDIANTE_ASIGNATURA_NO_DISPONIBLE);

        return estudianteAsignatura.get();
    }

    private void validarAsignatura(String idAsignatura) {
        if (idAsignatura != null && asignaturaService.getById(idAsignatura) == null)
            throw new ExceptionGtc(EL_ID + idAsignatura + ASIGNATURA_NO_DISPONIBLE);
    }

    private void validarEstudiante(String idEstudiante) {
        if (idEstudiante != null && estudianteService.getById(idEstudiante) == null)
            throw new ExceptionGtc(EL_ID + idEstudiante + ESTUDIANTE_NO_DISPONIBLE);
    }

    private EstudianteAsignaturaResDto validarCampoModificar(EstudianteAsignaturaInpDto inpDto, String id) {

        if (inpDto == null)
            throw new ExceptionGtc(INGRESE_INFORMACION_REQUERIDA);

        EstudianteAsignatura estudianteAsignatura = validarEstudianteAsignaturaBd(validarId(id));
        EstudianteAsignaturaInpDto db = mapper.aInpDto(estudianteAsignatura);
        if (db.equals(inpDto))
            mapper.aOutDto(estudianteAsignatura);

        validarAsignatura(inpDto.idAsignatura());
        validarEstudiante(inpDto.idEstudiante());

        estudianteAsignatura.setNota(inpDto.nota() != null ? stringADouble(inpDto.nota()) : estudianteAsignatura.getNota());
        estudianteAsignatura.setPeriodo(inpDto.periodo() != null ? stringAInteger(inpDto.periodo()) : estudianteAsignatura.getPeriodo());
        estudianteAsignatura.setIdAsignatura(inpDto.idAsignatura() != null ? stringALong(inpDto.idAsignatura()) : estudianteAsignatura.getIdAsignatura());
        estudianteAsignatura.setIdEstudiante(inpDto.idEstudiante() != null ? stringALong(inpDto.idEstudiante()) : estudianteAsignatura.getIdEstudiante());

        return mapper.aOutDto(estudianteAsignatura);
    }

}
