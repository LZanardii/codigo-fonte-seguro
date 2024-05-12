package br.com.unisinos.example.repository;

import br.com.unisinos.example.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

  List<UsuarioModel> findAllByCasePassword(String casePassword);
  List<UsuarioModel> findAllByCasePasswordAndNomeContaining(String casePassword, String nome);
}
