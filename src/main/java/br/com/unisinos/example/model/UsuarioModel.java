package br.com.unisinos.example.model;

import br.com.unisinos.example.dto.UsuarioDto;
import br.com.unisinos.example.dto.UsuarioStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String nome;

    @NonNull
    private String senha;

    @NonNull
    private LocalDate dataNascimento;

    @NonNull
    private String email;

    @NonNull
    private String status;

    @NonNull
    private String casePassword;

    public static UsuarioModel toUsuarioModel(UsuarioDto usuarioDto, UsuarioStatus status, String casePassword){
        return new UsuarioModel(
                usuarioDto.getNome(),
                usuarioDto.getSenha(),
                usuarioDto.getDataNascimento(),
                usuarioDto.getEmail(),
                status.getValue(),
                casePassword
        );
    }
}
