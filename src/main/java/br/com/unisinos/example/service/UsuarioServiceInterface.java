package br.com.unisinos.example.service;

import br.com.unisinos.example.dto.UsuarioDto;

import java.util.List;

public interface UsuarioServiceInterface {

  List<UsuarioDto> findUsuarioListByCase();
  List<UsuarioDto> findUsuarioListByNome(String nome);
  void saveVulneravel(UsuarioDto usuarioDto);
  void saveCorrigido(UsuarioDto usuarioDto);
}
