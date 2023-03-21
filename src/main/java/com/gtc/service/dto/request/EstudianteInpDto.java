package com.gtc.service.dto.request;

import javax.validation.constraints.*;

import static com.gtc.util.ExprecionRegular.NUMERO;
import static com.gtc.util.ExprecionRegular.NUMERO_DECIMAL;
import static com.gtc.util.MensajeError.*;

public record EstudianteInpDto(
        @NotBlank(message = INGRESE_DOCUMENTO)
        @NotNull(message = INGRESE_DOCUMENTO)
        @Pattern(regexp = NUMERO_DECIMAL + "{10}", message = DOCUMENTO_INVALIDO)
        String numeroDocumento,

        @NotBlank(message = INGRESE_NOMBRE)
        @NotNull(message = INGRESE_NOMBRE)
        String nombres,

        @NotBlank(message = INGRESE_APELLIDO)
        @NotNull(message = INGRESE_APELLIDO)
        String apellidos,

        @NotBlank(message = INGRESE_FECHA)
        @NotNull(message = INGRESE_FECHA)
        @Pattern(regexp = NUMERO + NUMERO_DECIMAL + "{3}-" + NUMERO_DECIMAL + "{2}-" + NUMERO_DECIMAL + "{2}",
                message = FECHA_INVALIDA)
        //@PastOrPresent(message = "La fecha ingresada es incorrecta")
        String fechaNacimiento,

        @NotBlank(message = INGRESE_DIRECCION)
        @NotNull(message = INGRESE_DIRECCION)
        String direccion,

        @NotBlank(message = INGRESE_EMAIL)
        @NotNull(message = INGRESE_EMAIL)
        @Email(message = CORREO_INVALIDO)
        String email,

        @Pattern(regexp = NUMERO_DECIMAL + "{7}", message = TELEFONO_INVALIDO)
        String fijo,

        @NotBlank(message = INGRESE_CELULAR)
        @NotNull(message = INGRESE_CELULAR)
        @Pattern(regexp = NUMERO_DECIMAL + "{10}", message = CELULAR_INVALIDO)
        String celular,

        @NotBlank(message = INGRESE_ID_GRADO)
        @NotNull(message = INGRESE_ID_GRADO)
        @Pattern(regexp = NUMERO + "+", message = ID_GRADO_INVALIDO)
        @Size(min = 1, max = 2000, message = "Tamano superado")
        String idGrado,

        @NotBlank(message = INGRESE_ID_TIPO_DOCUMENTO)
        @NotNull(message = INGRESE_ID_TIPO_DOCUMENTO)
        @Pattern(regexp = NUMERO + "+", message = ID_TIPO_DOCUMENTO_INVALIDO)
        @Size(min = 1, max = 2000, message = "Tamano superado")
        String idTipoDocumento,

        @NotBlank(message = "INGRESE_ID_TIPO_DOCUMENTO")
        @NotNull(message = "INGRESE_ID_TIPO_DOCUMENTO")
        @Pattern(regexp = NUMERO + "+", message = "ID_TIPO_DOCUMENTO_INVALIDO")
        @Size(min = 1, max = 2000, message = "Tamano superado")
        String idDane) {
}
