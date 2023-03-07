package com.gtc.controller;

import com.gtc.service.dto.request.EstudianteAsignaturaInpDto;
import com.gtc.service.dto.response.EstudianteAsignaturaResDto;
import com.gtc.service.impl.EstudianteAsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudiante/asignatura")
@RequiredArgsConstructor
public class EstudianteAsignaturaController {
    private final EstudianteAsignaturaService service;

    @GetMapping
    public ResponseEntity<List<EstudianteAsignaturaResDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EstudianteAsignaturaResDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<EstudianteAsignaturaResDto> save(@RequestBody @Valid EstudianteAsignaturaInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inpDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<EstudianteAsignaturaResDto> update(@PathVariable("id") Long id, @RequestBody EstudianteAsignaturaInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, inpDto));
    }
}
