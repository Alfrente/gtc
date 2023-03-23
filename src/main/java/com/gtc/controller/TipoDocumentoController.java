package com.gtc.controller;

import com.gtc.service.dto.request.TipoDocumentoInpDto;
import com.gtc.service.dto.response.TipoDocumentoResDto;
import com.gtc.service.impl.TipoDocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tipos_documento")
@RequiredArgsConstructor
public class TipoDocumentoController {

    private final TipoDocumentoService service;

    @GetMapping
    public ResponseEntity<List<TipoDocumentoResDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TipoDocumentoResDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<TipoDocumentoResDto> save(@RequestBody @Valid TipoDocumentoInpDto inpDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inpDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<TipoDocumentoResDto> update(@PathVariable("id") String id, @RequestParam("nuevoTipoDocumento")
    String nuevoTipoDocumento) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, nuevoTipoDocumento));
    }
}
