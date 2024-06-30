package br.com.unisinos.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Usuario_Senha")
public class UsuarioSenhaHistoricoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String senha;

	private LocalDateTime dataCadastro;

	@ManyToOne(fetch = FetchType.LAZY)
	private UsuarioModel usuario;

}
