package br.com.unisinos.example.dto;

import br.com.unisinos.example.model.UsuarioModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

  @Schema(example = "Daniel")
  private String nome;

  private LocalDate dataNascimento;

  @Schema(example = "ciberSecurity@gmail.com")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "E-mail inválido")
  private String email;

  @Schema(example = "suaMelhorSenha")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String senha;

  public static UsuarioDto toUsuarioDtoResponse(UsuarioModel usuarioModel){
    return UsuarioDto.builder()
            .nome(usuarioModel.getNome())
            .email(usuarioModel.getEmail())
            .dataNascimento(usuarioModel.getDataNascimento())
            .build();
  }

}
