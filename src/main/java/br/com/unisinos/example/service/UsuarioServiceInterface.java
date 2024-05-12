package br.com.unisinos.example.service;

import br.com.unisinos.example.dto.UsuarioDto;
import br.com.unisinos.example.exception.SenhaFracaException;
import br.com.unisinos.example.model.UsuarioModel;

import java.util.List;

public interface UsuarioServiceInterface {

  List<UsuarioModel> findUsuarioListByCase();
  List<UsuarioModel> findUsuarioListByNome(String nome);
  void saveVulneravel(UsuarioDto usuarioDto);
  void saveCorrigido(UsuarioDto usuarioDto) throws SenhaFracaException;
}
