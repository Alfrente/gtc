package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.EstudianteResponseMapper;
import com.gtc.mapper.IEstudianteConRelacionMapper;
import com.gtc.persistence.entity.Dane;
import com.gtc.persistence.entity.Estudiante;
import com.gtc.persistence.entity.Grado;
import com.gtc.persistence.entity.TipoDocumento;
import com.gtc.persistence.repository.IEstudianteRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.EstudiantePerRespDto;
import com.gtc.service.dto.request.EstudianteInpDto;
import com.gtc.service.dto.response.EstudianteResDto;
import com.gtc.service.dto.request.update.EstudianteInpUpdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.gtc.util.MensajeError.*;
import static com.gtc.util.MetodoCompartidos.*;

@Service
@RequiredArgsConstructor
public class EstudianteService implements ICrud<EstudianteResDto, EstudianteInpDto> {

    private final IEstudianteRepository repository;
    private final IEstudianteConRelacionMapper mapper;
    private final EstudianteResponseMapper estudianteResponseMapper;
    private final DaneService daneService;
    private final GradoService gradoService;
    private final TipoDocumentoService tipoDocumentoService;

    @Override
    public EstudianteResDto getById(String id) {
        return mapper.aOutDto(repository.findById(validarId(id)).orElse(null));
    }

    @Override
    public List<EstudianteResDto> getAll() {
        return mapper.aOutDtoList(getAllEstudiante());
    }

    @Override
    @Transactional
    public EstudianteResDto save(EstudianteInpDto dto) {
        validarFecha(dto.fechaNacimiento());
        validarDane((dto.idDane()));
        validarGrado(dto.idGrado());
        validarTipoDocumento(dto.idTipoDocumento());
        return mapper.aOutDto(repository.save(mapper.aEntidad(dto)));
    }

    @Override
    @Transactional
    public void delete(String id) {
        validarEstudianteBd(validarId(id));
        repository.deleteById(validarId(id));
    }

    @Transactional
    public EstudianteResDto update(String id, EstudianteInpUpdDto inpDto) {
        return validarCampoModificar(inpDto, id);
    }

    public Estudiante validarEstudianteBd(Long id) {
        if (id == null)
            throw new ExceptionGtc(ID_INVALIDO); //"El id esta nulo"

        Optional<com.gtc.persistence.entity.Estudiante> estudiante = repository.findById(id);
        if (estudiante.isEmpty())
            throw new ExceptionGtc(EL_ID + id + ESTUDIANTE_NO_DISPONIBLE);

        return estudiante.get();
    }

    public List<EstudiantePerRespDto> getEstudiantesPer() {
        return estudianteResponseMapper.aEstudiantePerList(getAllEstudiante());
    }

    public List<Estudiante> getAllEstudiante() {
        return repository.findAll();
    }

    private void validarDane(String idDane) {
        if (idDane != null && daneService.getById(idDane) == null)
            throw new ExceptionGtc(ID_DANE_INVALIDO); //"El id dane " + idDane + " no disponible"
    }

    private void validarGrado(String idGrado) {
        if (idGrado != null && gradoService.getById(idGrado) == null)
            throw new ExceptionGtc(ID_GRADO_INVALIDO); //"El id grado " + idGrado + " no disponible"
    }

    private void validarTipoDocumento(String idTipoDocumento) {
        if (idTipoDocumento != null && tipoDocumentoService.getById(idTipoDocumento) == null)
            throw new ExceptionGtc(ID_TIPO_DOCUMENTO_INVALIDO); //"El id tipo documento " + idTipoDocumento + " no disponible"
    }

    private EstudianteResDto validarCampoModificar(EstudianteInpUpdDto inpDto, String id) {

        if (inpDto == null)
            throw new ExceptionGtc(INGRESE_INFORMACION_REQUERIDA);

        Estudiante estudianteDb = validarEstudianteBd(validarId(id));
        EstudianteInpUpdDto db = mapper.aInpUpdDto(estudianteDb);
        if (db.equals(inpDto))
            return mapper.aOutDto(estudianteDb);

        Dane dane = daneService.validarDaneBd(stringALong(inpDto.idDane()));
        Grado grado = gradoService.validarGradoBd(stringALong(inpDto.idGrado()));
        TipoDocumento tipoDocumento = tipoDocumentoService.validarTipoDocumentoBd(stringALong(inpDto.idTipoDocumento()));

        validarFecha(inpDto.fechaNacimiento());

        estudianteDb.setNombres(inpDto.nombres() != null ? inpDto.nombres() : estudianteDb.getNombres());
        estudianteDb.setApellidos(inpDto.apellidos() != null ? inpDto.apellidos() : estudianteDb.getApellidos());
        estudianteDb.setDireccion(inpDto.direccion() != null ? inpDto.direccion() : estudianteDb.getDireccion());
        estudianteDb.setEmail(inpDto.email() != null ? inpDto.email() : estudianteDb.getEmail());
        estudianteDb.setFijo(inpDto.fijo() != null ? stringALong(inpDto.fijo()) : estudianteDb.getFijo());
        estudianteDb.setCelular(inpDto.celular() != null ? stringALong(inpDto.celular()) : estudianteDb.getCelular());
        estudianteDb.setIdGrado(inpDto.idGrado() != null ? stringALong(inpDto.idGrado()) : estudianteDb.getIdGrado());
        estudianteDb.setIdDane(inpDto.idDane() != null ? stringALong(inpDto.idDane()) : estudianteDb.getIdDane());
        estudianteDb.setIdTipoDocumento(inpDto.idTipoDocumento() != null ? stringALong(inpDto.idTipoDocumento()) :
                estudianteDb.getIdTipoDocumento());
        estudianteDb.setFechaNacimiento(inpDto.fechaNacimiento() != null ? LocalDate.parse(inpDto.fechaNacimiento()) :
                estudianteDb.getFechaNacimiento());
        estudianteDb.setNumeroDocumento(inpDto.numeroDocumento() != null ? stringALong(inpDto.numeroDocumento()) :
                estudianteDb.getNumeroDocumento());

        repository.save(estudianteDb);

        estudianteDb.setGrado(grado);
        estudianteDb.setTipoDocumento(tipoDocumento);
        estudianteDb.setDane(dane);

        return mapper.aOutDto(estudianteDb);
    }

}
