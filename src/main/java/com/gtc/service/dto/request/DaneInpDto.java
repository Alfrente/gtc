package com.gtc.service.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.gtc.util.ExprecionRegular.NUMERO;
import static com.gtc.util.MensajeError.*;

@Data
public class DaneInpDto {
    @NotBlank(message = INGRESE_DESCRIPCION)
    @NotNull(message = INGRESE_DESCRIPCION)
    private String descripcion;

    @NotBlank(message = INGRESE_ID_TIPO_DANE)
    @NotNull(message = INGRESE_ID_TIPO_DANE)
    @Pattern(regexp = NUMERO + "+", message = ID_TIPO_DANE_INVALIDO)
    private String idTipoDane;
}
