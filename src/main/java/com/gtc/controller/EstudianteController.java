package com.gtc.controller;

import com.gtc.service.dto.EstudiantePerRespDto;
import com.gtc.service.dto.request.EstudianteInpDto;
import com.gtc.service.dto.request.update.EstudianteInpUpdDto;
import com.gtc.service.dto.response.EstudianteResDto;
import com.gtc.service.impl.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {
    private final EstudianteService service;

    @GetMapping
    public ResponseEntity<List<EstudianteResDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/nota")
    public ResponseEntity<List<EstudiantePerRespDto>> getEstudianteNotas() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEstudiantesPer());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EstudianteResDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<EstudianteResDto> save(@RequestBody @Valid EstudianteInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inpDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<EstudianteResDto> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<EstudianteResDto> update(@PathVariable("id") String id, @RequestBody @Valid EstudianteInpUpdDto inpDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, inpDto));
    }
}
