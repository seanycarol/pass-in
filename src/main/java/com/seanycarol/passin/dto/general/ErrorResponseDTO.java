package com.seanycarol.passin.dto.general;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String message, int statusCode, LocalDateTime date) {
    
}
