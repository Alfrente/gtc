package com.gtc.controller;

import com.gtc.service.dto.request.DocenteInpDto;
import com.gtc.service.dto.response.DocenteResDto;
import com.gtc.service.impl.DocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/docentes")
@RequiredArgsConstructor
public class DocenteController {
    private final DocenteService service;

    @GetMapping
    public ResponseEntity<List<DocenteResDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DocenteResDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<DocenteResDto> save(@RequestBody @Valid DocenteInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inpDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<DocenteResDto> update(@PathVariable("id") String id, @RequestBody DocenteInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, inpDto));
    }
}
