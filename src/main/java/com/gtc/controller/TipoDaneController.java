package com.gtc.controller;

import com.gtc.service.dto.request.TipoDaneInpDto;
import com.gtc.service.dto.response.TipoDaneResDto;
import com.gtc.service.impl.TipoDaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tipos_dane")
@RequiredArgsConstructor
public class TipoDaneController {
    private final TipoDaneService service;

    @GetMapping
    public ResponseEntity<List<TipoDaneResDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TipoDaneResDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<TipoDaneResDto> save(@RequestBody @Valid TipoDaneInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inpDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<TipoDaneResDto> update(@PathVariable("id") String id, @RequestParam("tipoDane") String tipoDane) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, tipoDane));
    }
}
