package br.com.unisinos.example.exception;

import lombok.Getter;

import java.util.Collection;

import static br.com.unisinos.example.util.Constants.BAD_REQUEST_ERROR_SAVE_MESSAGE;

@Getter
public class SenhaFracaException extends RuntimeException {

  private final Collection<String> messages;

  public SenhaFracaException(Collection<String> messages){
    super(BAD_REQUEST_ERROR_SAVE_MESSAGE);
    this.messages = messages;
  }
}
