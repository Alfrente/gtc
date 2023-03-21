package com.gtc.service.dto;

import java.util.Set;

public record SDto(String nombre, String grado, Set<String> docentes,
                   Set<String> notas) {

}
