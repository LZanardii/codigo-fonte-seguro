package br.com.unisinos.example.case1.service;

import br.com.unisinos.example.dto.UsuarioDto;
import br.com.unisinos.example.exception.DataSaveErrorException;
import br.com.unisinos.example.exception.SenhaFracaException;
import br.com.unisinos.example.model.UsuarioModel;
import br.com.unisinos.example.repository.UsuarioRepository;
import br.com.unisinos.example.service.UsuarioServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static br.com.unisinos.example.model.UsuarioModel.toUsuarioModel;
import static br.com.unisinos.example.util.Constants.CASE_1_LABEL;

@RequiredArgsConstructor
@Service("UsuarioServiceImplCase1")
public class UsuarioServiceImpl implements UsuarioServiceInterface {

  final UsuarioRepository repository;
  final String INTERNAL_ERROR_SAVE_MESSAGE = "Erro ao salvar usuário";

  @Override
  public List<UsuarioModel> findUsuarioListByCase() {
    return repository.findAllByCasePassword(CASE_1_LABEL);
  }

  @Override
  public List<UsuarioModel> findUsuarioListByNome(String nome) {
    return repository.findAllByCasePasswordAndNomeContaining(CASE_1_LABEL, nome);
  }

  @Override
  public void saveVulneravel(UsuarioDto usuarioDto) {
    try {
      repository.save(toUsuarioModel(usuarioDto, CASE_1_LABEL));
    } catch (Exception e) {
      throw new DataSaveErrorException(INTERNAL_ERROR_SAVE_MESSAGE);
    }
  }

  @Override
  public void saveCorrigido(UsuarioDto usuarioDto) {
    if (this.isSenhaForte(usuarioDto.getSenha())) {
      try {
        repository.save(toUsuarioModel(usuarioDto, CASE_1_LABEL));
      } catch (Exception e) {
        throw new DataSaveErrorException(INTERNAL_ERROR_SAVE_MESSAGE);
      }
    }
  }

  //padrão de senha forte recomendado pela OAS -> https://www.oas.org/pt/cidh/portal/ayuda/estados/Nethelp/index.html#!Documents/dicasparaacriaodesen.htm
  private boolean isSenhaForte(String senha) {

    List<String> senhaForteInvalidMatches = new ArrayList<>();
    Pattern pattern;

    if (senha.length() < 8) {
      senhaForteInvalidMatches.add("Deve possuir ao menos 8 caracteres");
    }

    pattern = Pattern.compile("(.*\\d.*)(.*\\d.*)");

    if (!pattern.matcher(senha).find()) {
      senhaForteInvalidMatches.add("Deve possuir ao menos 2 caracteres numéricos");
    }

    pattern = Pattern.compile("[\\W_]");

    if (!pattern.matcher(senha).find()) {
      senhaForteInvalidMatches.add("Deve possuir ao menos 1 caractere especial");
    }

    pattern = Pattern.compile("[A-Z]");

    if (!pattern.matcher(senha).find()) {
      senhaForteInvalidMatches.add("Deve possuir ao menos 1 letra maiúscula");
    }

    pattern = Pattern.compile("[a-z]");

    if (!pattern.matcher(senha).find()) {
      senhaForteInvalidMatches.add("Deve possuir ao menos 1 letra minúscula");
    }

    if (!CollectionUtils.isEmpty(senhaForteInvalidMatches)) {
      throw new SenhaFracaException(senhaForteInvalidMatches);
    }

    return true;
  }
}