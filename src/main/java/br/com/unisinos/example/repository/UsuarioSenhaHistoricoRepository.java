package br.com.unisinos.example.repository;

import br.com.unisinos.example.model.UsuarioModel;
import br.com.unisinos.example.model.UsuarioSenhaHistoricoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioSenhaHistoricoRepository extends JpaRepository<UsuarioSenhaHistoricoModel, Integer> {

	boolean existsBySenhaAndUsuario(String senha, UsuarioModel usuario);

}
