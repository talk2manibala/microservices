package com.microservices.mstraining.model.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {

    private int code;
    private String message;
    private String details;
    private LocalDateTime timestamp;

}
