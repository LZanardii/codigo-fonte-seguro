package br.com.unisinos.example.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{

  HttpStatus status;

  public CustomException(String message, HttpStatus status){
    super(message);
    this.status = status;
  }
}
