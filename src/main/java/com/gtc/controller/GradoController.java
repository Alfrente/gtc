package com.gtc.controller;

import com.gtc.service.dto.request.EstudianteInpDto;
import com.gtc.service.dto.request.GradoInpDto;
import com.gtc.service.dto.request.update.EstudianteInpUpdDto;
import com.gtc.service.dto.response.EstudianteResDto;
import com.gtc.service.dto.response.GradoResDto;
import com.gtc.service.impl.GradoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grados")
@RequiredArgsConstructor
public class GradoController {
    private final GradoService service;

    @GetMapping
    public ResponseEntity<List<GradoResDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GradoResDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<GradoResDto> save(@RequestBody @Valid GradoInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inpDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<GradoResDto> update(@PathVariable("id") Long id, @RequestBody @Valid GradoInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, inpDto));
    }
}
