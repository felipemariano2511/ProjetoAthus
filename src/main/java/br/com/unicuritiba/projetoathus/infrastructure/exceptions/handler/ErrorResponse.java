package br.com.unicuritiba.projetoathus.infrastructure.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ErrorResponse {

    private String message;
    private LocalDateTime data;
    private int status;
    private String path;
}
