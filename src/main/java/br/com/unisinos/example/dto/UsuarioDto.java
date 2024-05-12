package br.com.unisinos.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioDto {

  private String nome;
  private String senha;
  private LocalDate dataNascimento;

}
