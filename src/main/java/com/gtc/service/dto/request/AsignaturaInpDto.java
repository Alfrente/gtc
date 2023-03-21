package com.gtc.service.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.gtc.util.ExprecionRegular.NUMERO;
import static com.gtc.util.MensajeError.*;

public record AsignaturaInpDto (
        @NotBlank(message = INGRESE_DESCRIPCION)
        @NotNull(message = INGRESE_DESCRIPCION)
        String descripcion,

        @NotBlank(message = INGRESE_ID_DOCENTE)
        @NotNull(message = INGRESE_ID_DOCENTE)
        @Pattern(regexp = NUMERO + "+", message = ID_DOCENTE_INVALIDO)
        String idDocente
){
}
