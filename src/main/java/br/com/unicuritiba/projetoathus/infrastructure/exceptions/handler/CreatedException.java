package br.com.unicuritiba.projetoathus.infrastructure.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CREATED)
public class CreatedException  extends RuntimeException{
    public CreatedException(String message) {
        super(message);

    }

    public CreatedException(String message, Throwable cause) {

        super(message,cause);
    }
}
