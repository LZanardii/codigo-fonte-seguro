package br.com.unisinos.example.dto;

import lombok.Getter;

@Getter
public enum UsuarioStatus {

  ATIVO("100"),
  INATIVO("300");

  private final String value;

  UsuarioStatus(String value) {
    this.value = value;
  }
}

