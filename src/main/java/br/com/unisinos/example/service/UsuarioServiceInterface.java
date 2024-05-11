package br.com.unisinos.example.service;

import br.com.unisinos.example.dto.UsuarioDto;

public interface UsuarioServiceInterface {

  void saveVulneravel(UsuarioDto usuarioDto);
  void saveCorrigido(UsuarioDto usuarioDto);
}
