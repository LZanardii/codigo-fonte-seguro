package br.com.unisinos.example.case2.controller;

import br.com.unisinos.example.case2.request.UsuarioRequest;
import br.com.unisinos.example.case2.service.UsuarioServiceImpl;
import br.com.unisinos.example.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/case-2/usuario")
@RestController("UsuarioControllerCase2")
@Tag(name = "Case 2", description = "senhas geradas aleatóriamente")
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
  public ResponseEntity<Void> saveVulneravel(@Valid @RequestBody UsuarioRequest usuarioRequest) {
    usuarioService.saveVulneravel(usuarioRequest);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/corrigido")
  public ResponseEntity<Void> saveCorrigido(@Valid @RequestBody UsuarioRequest usuarioRequest) {
    usuarioService.saveCorrigido(usuarioRequest);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}

