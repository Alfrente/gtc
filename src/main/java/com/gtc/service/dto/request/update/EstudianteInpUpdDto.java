package com.gtc.service.dto.request.update;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.gtc.util.ExprecionRegular.*;
import static com.gtc.util.MensajeError.*;

public record EstudianteInpUpdDto (
        @Pattern(regexp = NUMERO_DECIMAL + "{10}", message = DOCUMENTO_INVALIDO)
        String numeroDocumento,

        @Pattern(regexp = LETRA, message = INGRESE_NOMBRE)
        String nombres,

        @Pattern(regexp = LETRA, message = INGRESE_APELLIDO)
        String apellidos,

        @Pattern(regexp = NUMERO + NUMERO_DECIMAL + "{3}-" + NUMERO_DECIMAL + "{2}-" + NUMERO_DECIMAL + "{2}",
                message = FECHA_INVALIDA)
        String fechaNacimiento,

        String direccion,

        @Email(message = CORREO_INVALIDO)
        String email,

        @Pattern(regexp = NUMERO_DECIMAL + "{7}", message = TELEFONO_INVALIDO)
        String fijo,

        @Pattern(regexp = NUMERO_DECIMAL + "{10}", message = CELULAR_INVALIDO)
        String celular,

        @Pattern(regexp = NUMERO + "{6}", message = ID_GRADO_INVALIDO)
        @Size(min = 1, max = 2000, message = "Tamano superado")
        String idGrado,

        @Pattern(regexp = NUMERO + "{6}", message = "ID_Dane")    /*** **/
        @Size(min = 1, max = 2000, message = "Tamano superado")
        String idDane,
        @Pattern(regexp = NUMERO + "{6}", message = ID_TIPO_DOCUMENTO_INVALIDO)
        @Size(min = 1, max = 2000, message = "Tamano superado")
        String idTipoDocumento
){
}
