package br.com.unisinos.example.case3.controller;

import br.com.unisinos.example.case3.request.AlteracaoSenhaRequest;
import br.com.unisinos.example.case3.service.UsuarioServiceImpl;
import br.com.unisinos.example.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/case-3/usuario/senha/{id}")
@RestController
@Tag(name = "Case 2", description = "histórico de senhas para previnir reutilização de senhas")
public class UsuarioController {

	final UsuarioServiceImpl usuarioService;

	@GetMapping("/all")
	public List<UsuarioDto> getAllCase1Users() {
		return usuarioService.findUsuarioListByCase();
	}

	@GetMapping
	public List<UsuarioDto> getAllUsersByNome(String nome) {
		return usuarioService.findUsuarioListByNome(nome);
	}

	@PostMapping("/vulneravel")
	public ResponseEntity<Void> saveVulneravel(@RequestBody AlteracaoSenhaRequest request, @PathVariable Integer id) {
		usuarioService.atualizarSenhaVulneravel(id, request.getSenha());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/corrigido")
	public ResponseEntity<Void> saveCorrigido(@RequestBody AlteracaoSenhaRequest request, @PathVariable Integer id) {
		usuarioService.atualizarSenhaCorrigido(id, request.getSenha());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}

