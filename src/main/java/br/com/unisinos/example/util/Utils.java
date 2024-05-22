package br.com.unisinos.example.util;

import br.com.unisinos.example.exception.SenhaFracaException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {

  //padrão de senha forte recomendado pela OAS -> https://www.oas.org/pt/cidh/portal/ayuda/estados/Nethelp/index.html#!Documents/dicasparaacriaodesen.htm
  public static void isSenhaForte(String senha) {

    List<String> senhaForteInvalidMatches = new ArrayList<>();
    Pattern pattern;

    if (senha.length() < 8) {
      senhaForteInvalidMatches.add("Senha deve possuir ao menos 8 caracteres");
    }

    pattern = Pattern.compile("(.*\\d.*)(.*\\d.*)");

    if (!pattern.matcher(senha).find()) {
      senhaForteInvalidMatches.add("Senha deve possuir ao menos 2 caracteres numéricos");
    }

    pattern = Pattern.compile("[\\W_]");

    if (!pattern.matcher(senha).find()) {
      senhaForteInvalidMatches.add("Senha deve possuir ao menos 1 caractere especial");
    }

    pattern = Pattern.compile("[A-Z]");

    if (!pattern.matcher(senha).find()) {
      senhaForteInvalidMatches.add("Senha deve possuir ao menos 1 letra maiúscula");
    }

    pattern = Pattern.compile("[a-z]");

    if (!pattern.matcher(senha).find()) {
      senhaForteInvalidMatches.add("Senha deve possuir ao menos 1 letra minúscula");
    }

    if (!CollectionUtils.isEmpty(senhaForteInvalidMatches)) {
      throw new SenhaFracaException(senhaForteInvalidMatches);
    }
  }
}
