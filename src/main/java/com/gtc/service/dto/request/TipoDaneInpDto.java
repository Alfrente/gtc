package com.gtc.service.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record TipoDaneInpDto(
        @NotBlank(message = "Ingrese la descripcion")
        @NotNull(message = "Ingrese la descripcion")
        String descripcion
) {
}
