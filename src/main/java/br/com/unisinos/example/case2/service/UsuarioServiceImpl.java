package br.com.unisinos.example.case2.service;

import br.com.unisinos.example.dto.UsuarioDto;
import br.com.unisinos.example.dto.UsuarioStatus;
import br.com.unisinos.example.exception.DataErrorException;
import br.com.unisinos.example.repository.UsuarioRepository;
import br.com.unisinos.example.service.UsuarioServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.unisinos.example.model.UsuarioModel.toUsuarioModel;
import static br.com.unisinos.example.util.Constants.*;
import static br.com.unisinos.example.util.Utils.isSenhaForte;

@Slf4j
@RequiredArgsConstructor
@Service("UsuarioServiceImplCase2")
public class UsuarioServiceImpl implements UsuarioServiceInterface {

  final UsuarioRepository repository;

  @Override
  public List<UsuarioDto> findUsuarioListByCase() {
    return repository.findAllByCasePassword(CASE_2_LABEL)
            .stream()
            .map(UsuarioDto::toUsuarioDtoResponse)
            .collect(Collectors.toList());
  }

  @Override
  public List<UsuarioDto> findUsuarioListByNome(String nome) {
    return repository.findAllByCasePasswordAndNomeContainingIgnoreCase(CASE_2_LABEL, nome)
            .stream()
            .map(UsuarioDto::toUsuarioDtoResponse)
            .collect(Collectors.toList());
  }

  @Override
  public void saveVulneravel(UsuarioDto usuarioDto) {
    try {
      usuarioDto.setSenha(this.buildWeakPassword(usuarioDto.getEmail()));
      repository.save(toUsuarioModel(usuarioDto, UsuarioStatus.ATIVO, CASE_2_LABEL));
      this.enviaEmailConfirmcaoCadastro(usuarioDto);
    } catch (Exception e) {
      throw new DataErrorException(INTERNAL_ERROR_SAVE_MESSAGE);
    }
  }

  @Override
  public void saveCorrigido(UsuarioDto usuarioDto) {
    String senha = this.buildStrongPassword();
    isSenhaForte(senha);
    usuarioDto.setSenha(senha);
    try {
      repository.save(toUsuarioModel(usuarioDto, UsuarioStatus.ATIVO, CASE_2_LABEL));
      this.enviaEmailConfirmcaoCadastro(usuarioDto);
    } catch (Exception e) {
      throw new DataErrorException(INTERNAL_ERROR_SAVE_MESSAGE);
    }
  }

  private String buildWeakPassword(String email) {
    try {
      String prefixoSenha = email.split("@")[0];
      return String.format("%s-123", prefixoSenha);
    } catch (Exception e) {
      throw new DataErrorException("Erro ao criar senha temporária");
    }
  }

  private String buildStrongPassword() {

    String[] caracteresMaiusculos = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"
    };

    String[] caracteresMinusculos = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
            "x", "y", "z"
    };

    String[] caracteresEspeciais = {
            "?", "!", "(", ")", "[", "]", "{", "}", "—", "+", "-", "*", "/",
            "=", "$", "%", ">", "<", "&", "|", "~", "^", "_", "*", "#", "%", "@"
    };

    String[] caracteresNumerais = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    StringBuilder senha = new StringBuilder();

    for (int i = 0; i < 2; i++) {
      int posicao = (int) (Math.random() * caracteresMinusculos.length);
      senha.append(caracteresMinusculos[posicao]);
    }

    for (int i = 0; i < 2; i++) {
      int posicao = (int) (Math.random() * caracteresMaiusculos.length);
      senha.append(caracteresMaiusculos[posicao]);
    }

    for (int i = 0; i < 2; i++) {
      int posicao = (int) (Math.random() * caracteresNumerais.length);
      senha.append(caracteresNumerais[posicao]);
    }

    for (int i = 0; i < 2; i++) {
      int posicao = (int) (Math.random() * caracteresEspeciais.length);
      senha.append(caracteresEspeciais[posicao]);
    }
    
    return this.embaralharSenha(senha.toString());
  }

  private String embaralharSenha(String input) {

    List<Character> caracteres = new ArrayList<>();

    for (char c : input.toCharArray()) {
      caracteres.add(c);
    }

    Collections.shuffle(caracteres);

    StringBuilder resultado = new StringBuilder(caracteres.size());

    for (char c : caracteres) {
      resultado.append(c);
    }

    return resultado.toString();
  }


  //TODO -> IMPLEMENTAR FLUXO DE ENVIO DE E-MAILS POR SMTP OU AMAZON SES
  private void enviaEmailConfirmcaoCadastro(UsuarioDto usuarioDto) {
    log.info("Olá {}! Sua senha temporária é: {}", usuarioDto.getNome(), usuarioDto.getSenha());
  }
}
