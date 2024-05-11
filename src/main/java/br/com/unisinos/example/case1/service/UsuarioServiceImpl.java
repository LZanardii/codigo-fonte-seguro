package br.com.unisinos.example.case1.service;

import br.com.unisinos.example.dto.UsuarioDto;
import br.com.unisinos.example.repository.UsuarioRepository;
import br.com.unisinos.example.service.UsuarioServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("UsuarioServiceImplCase1")
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioServiceInterface {

  final UsuarioRepository repository;

  @Override
  public void saveVulneravel(UsuarioDto usuarioDto) {

  }

  @Override
  public void saveCorrigido(UsuarioDto usuarioDto) {

  }
}