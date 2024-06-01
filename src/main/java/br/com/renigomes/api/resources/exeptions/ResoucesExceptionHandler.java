package br.com.renigomes.api.resources.exeptions;

import br.com.renigomes.api.Service.exceptions.DataInterativeViolationException;
import br.com.renigomes.api.Service.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResoucesExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardErrorExeption> objectNotFound(ObjectNotFoundException e,
                                                                HttpServletRequest request) {
        StandardErrorExeption error = new StandardErrorExeption(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataInterativeViolationException.class)
    public ResponseEntity<StandardErrorExeption> dataInterativeViolationException(DataInterativeViolationException e,
                                                                HttpServletRequest request){
        StandardErrorExeption error = new StandardErrorExeption(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }
}
