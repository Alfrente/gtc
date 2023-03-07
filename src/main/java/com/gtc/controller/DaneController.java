package com.gtc.controller;

import com.gtc.service.dto.request.DaneInpDto;
import com.gtc.service.dto.response.DaneResDto;
import com.gtc.service.impl.DaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dane")
@RequiredArgsConstructor
public class DaneController {

    private final DaneService service;

    @GetMapping
    public ResponseEntity<List<DaneResDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DaneResDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<DaneResDto> save(@RequestBody @Valid DaneInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inpDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<DaneResDto> update(@PathVariable("id") Long id, @RequestBody DaneInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, inpDto));
    }
}
