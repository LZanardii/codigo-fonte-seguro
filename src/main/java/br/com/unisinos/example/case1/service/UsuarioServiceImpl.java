package br.com.unisinos.example.case1.service;

import br.com.unisinos.example.dto.UsuarioDto;
import br.com.unisinos.example.dto.UsuarioStatus;
import br.com.unisinos.example.exception.DataErrorException;
import br.com.unisinos.example.repository.UsuarioRepository;
import br.com.unisinos.example.service.UsuarioServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.unisinos.example.model.UsuarioModel.toUsuarioModel;
import static br.com.unisinos.example.util.Constants.CASE_1_LABEL;
import static br.com.unisinos.example.util.Constants.INTERNAL_ERROR_SAVE_MESSAGE;
import static br.com.unisinos.example.util.Utils.isSenhaForte;

@RequiredArgsConstructor
@Service("UsuarioServiceImplCase1")
public class UsuarioServiceImpl implements UsuarioServiceInterface {

  final UsuarioRepository repository;

  @Override
  public List<UsuarioDto> findUsuarioListByCase() {
    return repository.findAllByCasePassword(CASE_1_LABEL)
            .stream()
            .map(UsuarioDto::toUsuarioDtoResponse)
            .collect(Collectors.toList());
  }

  @Override
  public List<UsuarioDto> findUsuarioListByNome(String nome) {
    return repository.findAllByCasePasswordAndNomeContainingIgnoreCase(CASE_1_LABEL, nome)
            .stream()
            .map(UsuarioDto::toUsuarioDtoResponse)
            .collect(Collectors.toList());
  }

  @Override
  public void saveVulneravel(UsuarioDto usuarioDto) {
    try {
      repository.save(toUsuarioModel(usuarioDto, UsuarioStatus.ATIVO, CASE_1_LABEL));
    } catch (Exception e) {
      throw new DataErrorException(INTERNAL_ERROR_SAVE_MESSAGE);
    }
  }

  @Override
  public void saveCorrigido(UsuarioDto usuarioDto) {
    isSenhaForte(usuarioDto.getSenha());

    try {
      repository.save(toUsuarioModel(usuarioDto, UsuarioStatus.ATIVO, CASE_1_LABEL));
    } catch (Exception e) {
      throw new DataErrorException(INTERNAL_ERROR_SAVE_MESSAGE);
    }
  }

}