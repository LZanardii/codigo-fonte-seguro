package br.com.unisinos.example.case3.service;

import br.com.unisinos.example.dto.UsuarioDto;
import br.com.unisinos.example.exception.DataErrorException;
import br.com.unisinos.example.repository.UsuarioRepository;
import br.com.unisinos.example.repository.UsuarioSenhaHistoricoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.unisinos.example.util.Constants.BAD_REQUEST_ERROR_DUPLICATE_MESSAGE;
import static br.com.unisinos.example.util.Constants.INTERNAL_ERROR_SAVE_MESSAGE;
import static br.com.unisinos.example.util.Utils.isSenhaForte;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl {

	private final UsuarioRepository usuarioRepository;
	private final UsuarioSenhaHistoricoRepository usuarioSenhaHistoricoRepository;

	public List<UsuarioDto> findUsuarioListByCase() {
		return usuarioRepository.findAll()
				.stream()
				.map(UsuarioDto::toUsuarioDtoResponse)
				.collect(Collectors.toList());
	}

	public List<UsuarioDto> findUsuarioListByNome(String nome) {
		return usuarioRepository.findAllByNomeContainingIgnoreCase(nome)
				.stream()
				.map(UsuarioDto::toUsuarioDtoResponse)
				.collect(Collectors.toList());
	}

	public void atualizarSenhaVulneravel(Integer id, String senha) {
		isSenhaForte(senha);

		var usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new DataErrorException(INTERNAL_ERROR_SAVE_MESSAGE));

		try {
			usuario.setSenha(senha);
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			throw new DataErrorException(INTERNAL_ERROR_SAVE_MESSAGE);
		}
	}

	public void atualizarSenhaCorrigido(Integer id, String senha) {
		isSenhaForte(senha);

		var usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new DataErrorException(INTERNAL_ERROR_SAVE_MESSAGE));

		if (usuarioSenhaHistoricoRepository.existsBySenhaAndUsuario(senha, usuario)) {
			throw new DataErrorException(BAD_REQUEST_ERROR_DUPLICATE_MESSAGE);
		}

		try {
			usuario.setSenha(senha);
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			throw new DataErrorException(INTERNAL_ERROR_SAVE_MESSAGE);
		}
	}

}