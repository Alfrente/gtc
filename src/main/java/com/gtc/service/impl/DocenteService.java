package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.IDocenteConRelacionMapper;
import com.gtc.persistence.entity.Docente;
import com.gtc.persistence.entity.Grado;
import com.gtc.persistence.entity.TipoDocumento;
import com.gtc.persistence.repository.IDocenteRepository;
import com.gtc.service.ICrud;
import com.gtc.service.dto.request.DocenteInpDto;
import com.gtc.service.dto.response.DocenteResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.gtc.util.MensajeError.*;
import static com.gtc.util.MetodoCompartidos.stringALong;
import static com.gtc.util.MetodoCompartidos.validarId;

@Service
@RequiredArgsConstructor
public class DocenteService implements ICrud<DocenteResDto, DocenteInpDto> {

    private final IDocenteRepository repository;
    private final IDocenteConRelacionMapper relacionMapper;
    private final TipoDocumentoService tipoDocumentoService;
    private final GradoService gradoService;

    @Override
    public DocenteResDto getById(String id) {
        return relacionMapper.aOutDto(repository.findById(validarId(id)).orElse(null));
    }

    @Override
    public List<DocenteResDto> getAll() {
        return relacionMapper.aOutDtoList(repository.findAll());
    }

    @Override
    @Transactional
    public DocenteResDto save(DocenteInpDto inpDto) {
        validarTipoDocumento(inpDto.idTipoDocumento());
        validarGrado(inpDto.idGradoResponsable());
        return relacionMapper.aOutDto(relacionMapper.aEntidad(inpDto));
    }

    @Override
    @Transactional
    public void delete(String id) {
        validarDocenteIdBd(validarId(id));
        repository.deleteById(validarId(id));
    }

    @Transactional
    public DocenteResDto update(String id, DocenteInpDto inpDto) {
        return validarCampoModificar(inpDto, validarId(id));
    }

    public Docente validarDocenteIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc(ID_INVALIDO);

        Optional<Docente> docente = repository.findById(id);
        if (docente.isEmpty())
            throw new ExceptionGtc(EL_ID + id + DOCENTE_NO_DISPONIBLE);

        return docente.get();
    }

    private void validarTipoDocumento(String idTipoDocumento) {
        if (idTipoDocumento != null && tipoDocumentoService.getById(idTipoDocumento) == null)
            throw new ExceptionGtc(EL_ID + idTipoDocumento + TIPO_DOCUMENTO_NO_DISPONIBLE);
    }

    private void validarGrado(String idGrado) {
        if (idGrado != null && gradoService.getById(idGrado) == null)
            throw new ExceptionGtc(EL_ID + idGrado + GRADO_NO_DISPONIBLE);
    }

    private DocenteResDto validarCampoModificar(DocenteInpDto inpDto, Long id) {

        if (inpDto == null)
            throw new ExceptionGtc(INGRESE_INFORMACION_REQUERIDA);

        Docente docente = validarDocenteIdBd(id);
        DocenteInpDto db = relacionMapper.aInpDto(docente);
        if (db.equals(inpDto))
            return relacionMapper.aOutDto(docente);

        TipoDocumento tipoDocumento = tipoDocumentoService.validarTipoDocumentoBd(stringALong(inpDto.idTipoDocumento()));
        Grado grado = gradoService.validarGradoBd(stringALong(inpDto.idGradoResponsable()));

        docente.setNumeroDocumento(inpDto.numeroDocumento() != null ? stringALong(inpDto.numeroDocumento()) : docente.getNumeroDocumento());
        docente.setNombres(inpDto.nombres() != null ? inpDto.nombres() : docente.getNombres());
        docente.setApellidos(inpDto.apellidos() != null ? inpDto.apellidos() : docente.getApellidos());
        docente.setFechaNacimiento(inpDto.fechaNacimiento() != null ? LocalDate.parse(inpDto.fechaNacimiento()) : docente.getFechaNacimiento());
        docente.setAsigDictadas(inpDto.asigDictadas() != null ? inpDto.asigDictadas() : docente.getAsigDictadas());
        docente.setGradoEscolaridad(inpDto.gradoEscolaridad() != null ? inpDto.gradoEscolaridad() : docente.getGradoEscolaridad());
        docente.setEmail(inpDto.email() != null ? inpDto.email() : docente.getEmail());
        docente.setFijo(inpDto.fijo() != null ? stringALong(inpDto.fijo()) : docente.getFijo());
        docente.setCelular(inpDto.celular() != null ? stringALong(inpDto.celular()) : docente.getCelular());

        repository.save(docente);

        docente.setTipoDocumento(tipoDocumento);
        docente.setGrado(grado);

        return relacionMapper.aOutDto(docente);
    }
}
