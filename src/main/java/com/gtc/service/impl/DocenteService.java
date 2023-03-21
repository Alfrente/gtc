package com.gtc.service.impl;

import com.gtc.exception.ExceptionGtc;
import com.gtc.mapper.IDocenteConRelacionMapper;
import com.gtc.mapper.IDocenteMapper;
import com.gtc.persistence.entity.Docente;
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

import static com.gtc.util.MetodosEstatico.longValue;

@Service
@RequiredArgsConstructor
public class DocenteService implements ICrud<DocenteResDto, DocenteInpDto> {

    private final IDocenteRepository repository;
    private final IDocenteConRelacionMapper relacionMapper;
    private final IDocenteMapper mapper; //
    private final TipoDocumentoService tipoDocumentoService;
    private final GradoService gradoService;

    @Override
    public DocenteResDto getById(Long id) {
        return relacionMapper.aOutDto(repository.findById(id).orElse(null));
    }

    @Override
    public List<DocenteResDto> getAll() {
        return relacionMapper.aOutDtoList(repository.findAll());
    }

    @Override
    @Transactional
    public DocenteResDto save(DocenteInpDto inpDto) {
        validarTipoDocumento(longValue(inpDto.idTipoDocumento()));
        validarGrado(longValue(inpDto.idGradoResponsable()));
        return relacionMapper.aOutDto(relacionMapper.aEntidad(inpDto));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarDocenteIdBd(id);
        repository.deleteById(id);
    }

    @Transactional
    public DocenteResDto update(Long id, DocenteInpDto inpDto) {
        Docente docente = validarDocenteIdBd(id);
        validarTipoDocumento(longValue(inpDto.idTipoDocumento()));
        validarGrado(longValue(inpDto.idGradoResponsable()));
        return relacionMapper.aOutDto(repository.save(validarCampoModificar(inpDto, docente)));
    }

    private Docente validarDocenteIdBd(Long id) {
        if (id == null)
            throw new ExceptionGtc("El id esta nulo");

        Optional<com.gtc.persistence.entity.Docente> docente = repository.findById(id);
        if (docente.isEmpty())
            throw new ExceptionGtc("El id " + id + " no esta disponible");

        return docente.get();
    }

    private Docente validarCampoModificar(DocenteInpDto inpDto, Docente docente) {

        if (inpDto == null)
            throw new ExceptionGtc("Ingrese la informacion requerida");

        docente.setNumeroDocumento(inpDto.numeroDocumento() != null ? longValue(inpDto.numeroDocumento()) : docente.getNumeroDocumento());
        docente.setNombres(inpDto.nombres() != null ? inpDto.nombres() : docente.getNombres());
        docente.setApellidos(inpDto.apellidos() != null ? inpDto.apellidos() : docente.getApellidos());
        docente.setFechaNacimiento(inpDto.fechaNacimiento() != null ? LocalDate.parse(inpDto.fechaNacimiento()) : docente.getFechaNacimiento());
        docente.setAsigDictadas(inpDto.asigDictadas() != null ? inpDto.asigDictadas() : docente.getAsigDictadas());
        docente.setGradoEscolaridad(inpDto.gradoEscolaridad() != null ? inpDto.gradoEscolaridad() : docente.getGradoEscolaridad());
        docente.setEmail(inpDto.email() != null ? inpDto.email() : docente.getEmail());
        docente.setFijo(inpDto.fijo() != null ? longValue(inpDto.fijo()) : docente.getFijo());
        docente.setCelular(inpDto.celular() != null ? longValue(inpDto.celular()) : docente.getCelular());

        return docente;
    }
    private void validarTipoDocumento(Long idTipoDocumento) {
        if (idTipoDocumento != null && tipoDocumentoService.getById(idTipoDocumento) == null)
            throw new ExceptionGtc("El id tipo documento " + idTipoDocumento + " no disponible");
    }

    private void validarGrado(Long idGrado) {
        if (idGrado != null && gradoService.getById(idGrado) == null)
            throw new ExceptionGtc("El id grado " + idGrado + " no disponible");
    }
}
