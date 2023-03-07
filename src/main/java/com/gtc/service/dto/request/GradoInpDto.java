package com.gtc.service.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class GradoInpDto {
    @NotBlank(message = "Ingrese la descripcion")
    @NotNull(message = "Ingrese la descripcion")
    private String descripcion;
}
