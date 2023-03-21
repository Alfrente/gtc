package com.gtc.service.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record TipoDocumentoInpDto(
        @NotBlank(message = "Ingrese la descripcion")
        @NotNull(message = "Ingrese la descripcion")
        String descripcion) {
}
