package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.EstudianteResponseMapper;
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

import static com.gtc.util.MetodosEstatico.longValue;
import static com.gtc.util.MetodosEstatico.validarFecha;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstudianteService implements ICrud<EstudianteResDto, EstudianteInpDto> {

    private final IEstudianteRepository repository;
    private final IEstudianteConRelacionMapper mapper;

    private final EstudianteResponseMapper estudianteResponseMapper;
    private final DaneService daneService;
    private final GradoService gradoService;
    private final TipoDocumentoService tipoDocumentoService;

    @Override
    public EstudianteResDto getById(Long id) {
        return mapper.aOutDto(repository.findById(id).orElse(null));
    }

    @Override
    public List<EstudianteResDto> getAll() {
        return mapper.aOutDtoList(getAllEstudiante());
    }

    @Override
    @Transactional
    public EstudianteResDto save(EstudianteInpDto dto) { //
        validarFecha(dto.fechaNacimiento());
        validarDane(longValue(dto.idDane()));
        validarGrado(longValue(dto.idGrado()));
        validarTipoDocumento(longValue(dto.idTipoDocumento()));
        return mapper.aOutDto(repository.save(mapper.aEntidad(dto)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarIdBd(id);
        repository.deleteById(id);
    }

  /*
    @Transactional
    public EstudianteResDto update(Long id, EstudianteInpDto estudianteDto) {
        Estudiante estudianteDb = validarIdBd(id);
        return mapper.aOutDto(repository.save(validarCampoModificar(estudianteDto, estudianteDb)));
    }*/

    @Transactional
    public EstudianteResDto update(Long id, EstudianteInpUpdDto inpDto) { //
        Estudiante estudianteDb = validarIdBd(id);
        validarDane(longValue(inpDto.idDane()));
        validarGrado(longValue(inpDto.idGrado()));
        validarTipoDocumento(longValue(inpDto.idTipoDocumento()));
        return mapper.aOutDto(repository.save(validarCampoModificar(inpDto, estudianteDb)));
    }

    private Estudiante validarIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<com.gtc.persistence.entity.Estudiante> estudiante = repository.findById(id);
        if (estudiante.isEmpty())
            throw new ExceptionGtc("El id " + id + " no esta disponible");

        return estudiante.get();
    }

    private Estudiante validarCampoModificar(Object inpDto, Estudiante estudianteDb) {

        if (inpDto == null)
            throw new ExceptionGtc("Ingrese la informacion requerida");

        EstudianteInpDto dato = (EstudianteInpDto) inpDto;

        validarFecha(dato.fechaNacimiento());

        estudianteDb.setNumeroDocumento(dato.numeroDocumento() != null ? longValue(dato.numeroDocumento()) :
                estudianteDb.getNumeroDocumento());
        estudianteDb.setNombres(dato.nombres() != null ? dato.nombres() : estudianteDb.getNombres());
        estudianteDb.setApellidos(dato.apellidos() != null ? dato.apellidos() : estudianteDb.getApellidos());
        estudianteDb.setFechaNacimiento(dato.fechaNacimiento() != null ? LocalDate.parse(dato.fechaNacimiento()) :
                estudianteDb.getFechaNacimiento());
        estudianteDb.setDireccion(dato.direccion() != null ? dato.direccion() : estudianteDb.getDireccion());
        estudianteDb.setEmail(dato.email() != null ? dato.email() : estudianteDb.getEmail());
        estudianteDb.setFijo(dato.fijo() != null ? longValue(dato.fijo()) : estudianteDb.getFijo());
        estudianteDb.setCelular(dato.celular() != null ? longValue(dato.celular()) : estudianteDb.getCelular());
        estudianteDb.getGrado().setId(dato.idGrado() != null ? longValue(dato.idGrado()) :
                estudianteDb.getGrado().getId());
        estudianteDb.getTipoDocumento().setId(dato.idGrado() != null ? longValue(dato.idGrado()) :
                estudianteDb.getTipoDocumento().getId());

        return estudianteDb;
    }

    public List<SDto> getEstudiantesJoinNota() {
        return estudianteResponseMapper.aSDtoList(getAllEstudiante());
    }

    public List<Estudiante> getAllEstudiante() {
        return repository.findAll();
    }

    private void validarDane(Long idDane) {
        if (idDane != null && daneService.getById(idDane) == null)
            throw new ExceptionGtc("El id dane " + idDane + " no disponible");
    }

    private void validarGrado(Long idGrado) {
        if (idGrado != null && gradoService.getById(idGrado) == null)
            throw new ExceptionGtc("El id grado " + idGrado + " no disponible");
    }

    private void validarTipoDocumento(Long idTipoDocumento) {
        if (idTipoDocumento != null && tipoDocumentoService.getById(idTipoDocumento) == null)
            throw new ExceptionGtc("El id tipo documento " + idTipoDocumento + " no disponible");
    }

}
