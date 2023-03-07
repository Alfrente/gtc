package com.gtc.service.dto.request.update;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

import static com.gtc.util.ExprecionRegular.*;
import static com.gtc.util.MensajeError.*;

@Data
public class EstudianteInpUpdDto {

    //@NotBlank(message = INGRESE_DOCUMENTO)
    @Pattern(regexp = NUMERO_DECIMAL + "{10}", message = DOCUMENTO_INVALIDO)
    private String numeroDocumento;

    // @NotBlank(message = )
    @Pattern(regexp = LETRA, message = INGRESE_NOMBRE)
    private String nombres;

    //@NotBlank(message = INGRESE_APELLIDO)
    @Pattern(regexp = LETRA, message = INGRESE_APELLIDO)
    private String apellidos;

    //@NotBlank(message = INGRESE_FECHA)
    @Pattern(regexp = NUMERO + NUMERO_DECIMAL + "{3}-" + NUMERO_DECIMAL + "{2}-" + NUMERO_DECIMAL + "{2}",
            message = FECHA_INVALIDA)
    private String fechaNacimiento;

    //@NotBlank(message = INGRESE_DIRECCION)
    private String direccion;

    //@NotBlank(message = INGRESE_EMAIL)
    @Email(message = CORREO_INVALIDO)
    private String email;

    @Pattern(regexp = NUMERO_DECIMAL + "{7}", message = TELEFONO_INVALIDO)
    private String fijo;

    // @NotBlank(message = INGRESE_CELULAR)
    @Pattern(regexp = NUMERO_DECIMAL + "{10}", message = CELULAR_INVALIDO)
    private String celular;

    // @NotBlank(message = INGRESE_ID_GRADO)
    @Pattern(regexp = NUMERO + "{6}", message = ID_GRADO_INVALIDO)
    @Size(min = 1, max = 2000, message = "Tamano superado")
    private String idGrado;

    //@NotBlank(message = INGRESE_ID_TIPO_DOCUMENTO)
    @Pattern(regexp = NUMERO + "{6}", message = ID_TIPO_DOCUMENTO_INVALIDO)
    @Size(min = 1, max = 2000, message = "Tamano superado")
    private String idTipoDocumento;
}
