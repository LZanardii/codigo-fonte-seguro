package br.com.unisinos.example.exception;

import lombok.Getter;

import java.util.Collection;

@Getter
public class SenhaFracaException extends RuntimeException {

  private final Collection<String> messages;

  public SenhaFracaException(Collection<String> messages){
    super("Senha inválida");
    this.messages = messages;
  }
}
