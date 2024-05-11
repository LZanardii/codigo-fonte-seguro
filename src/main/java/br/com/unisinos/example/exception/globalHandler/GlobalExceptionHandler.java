package br.com.unisinos.example.exception.globalHandler;


import br.com.unisinos.example.exception.CustomException;
import br.com.unisinos.example.exception.EmptyDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.I_AM_A_TEAPOT;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("details:", errors);
        return new ResponseEntity<>(body, BAD_REQUEST);
    }

    @ExceptionHandler(EmptyDataException.class)
    public final ResponseEntity<ErrorResponse> emptyDataException(Exception ex){
        log.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("I_AM_A_TEAPOT", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, I_AM_A_TEAPOT);
    }

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ErrorResponse> customException(CustomException ex){
        log.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Uepa", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
}
