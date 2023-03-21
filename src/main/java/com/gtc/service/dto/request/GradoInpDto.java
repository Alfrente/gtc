package com.gtc.service.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record GradoInpDto(
        @NotBlank(message = "Ingrese la descripcion")
        @NotNull(message = "Ingrese la descripcion")
        String descripcion) {
}
