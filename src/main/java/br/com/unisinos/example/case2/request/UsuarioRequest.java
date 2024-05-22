package br.com.unisinos.example.case2.request;

import br.com.unisinos.example.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest extends UsuarioDto {

  @Hidden
  private String senha;
}
