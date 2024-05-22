package br.com.unisinos.example.exception.globalHandler;

import br.com.unisinos.example.exception.DataErrorException;
import br.com.unisinos.example.exception.SenhaFracaException;
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
import java.util.List;
import java.util.stream.Collectors;

import static br.com.unisinos.example.util.Constants.BAD_REQUEST_ERROR_SAVE_MESSAGE;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {


        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST_ERROR_SAVE_MESSAGE, errors);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(SenhaFracaException.class)
    public final ResponseEntity<ErrorResponse> senhaFracaException(SenhaFracaException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getMessages());
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(DataErrorException.class)
    public final ResponseEntity<ErrorResponse> dataSaveErrorException(Exception ex){
        log.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Erro interno ao salvar usuário", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }
}
