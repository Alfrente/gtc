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

@Service
@RequiredArgsConstructor
public class DocenteService implements ICrud<DocenteResDto, DocenteInpDto> {

    private final IDocenteRepository repository;
    private final IDocenteConRelacionMapper relacionMapper;
    private final IDocenteMapper mapper;

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
    public DocenteResDto save(DocenteInpDto dto) {
        return relacionMapper.aOutDto(relacionMapper.aEntidad(dto));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarDocenteIdBd(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public DocenteResDto update(Long id, DocenteInpDto inpDto) {
        Docente docente = validarDocenteIdBd(id);
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

        docente.setNumeroDocumento(inpDto.getNumeroDocumento() != null ? longValue(inpDto.getNumeroDocumento()) : docente.getNumeroDocumento());
        docente.setNombres(inpDto.getNombres() != null ? inpDto.getNombres() : docente.getNombres());
        docente.setApellidos(inpDto.getApellidos() != null ? inpDto.getApellidos() : docente.getApellidos());
        docente.setFechaNacimiento(inpDto.getFechaNacimiento() != null ? LocalDate.parse(inpDto.getFechaNacimiento()) : docente.getFechaNacimiento());
        docente.setAsigDictadas(inpDto.getAsigDictadas() != null ? inpDto.getAsigDictadas() : docente.getAsigDictadas());
        docente.setGradoEscolaridad(inpDto.getGradoEscolaridad() != null ? inpDto.getGradoEscolaridad() : docente.getGradoEscolaridad());
        docente.setEmail(inpDto.getEmail() != null ? inpDto.getEmail() : docente.getEmail());
        docente.setFijo(inpDto.getFijo() != null ? longValue(inpDto.getFijo()) : docente.getFijo());
        docente.setCelular(inpDto.getCelular() != null ? longValue(inpDto.getCelular()) : docente.getCelular());

        return docente;
    }

    private Long longValue(String valor) {
        return Long.valueOf(valor);
    }
}
