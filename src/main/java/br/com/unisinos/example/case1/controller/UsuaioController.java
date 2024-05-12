package br.com.unisinos.example.case1.controller;

import br.com.unisinos.example.case1.service.UsuarioServiceImpl;
import br.com.unisinos.example.dto.UsuarioDto;
import br.com.unisinos.example.model.UsuarioModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/case-1/usuario")
@RestController("UsuarioControllerCase1")
@Tag(name = "Case 1 ", description = "senhas sem validações")
public class UsuaioController {

  final UsuarioServiceImpl usuarioService;

  @GetMapping("/all")
  public List<UsuarioModel> getAllCase1Users() {
    return usuarioService.findUsuarioListByCase();
  }

  @GetMapping
  public List<UsuarioModel> getAllUsersByNome(String nome) {
    return usuarioService.findUsuarioListByNome(nome);
  }

  @PostMapping("/vulneravel")
  public ResponseEntity<Void> saveVulneravel(@RequestBody UsuarioDto usuarioDto) {
    usuarioService.saveVulneravel(usuarioDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/corrigido")
  public ResponseEntity<Void> saveCorrigido(@RequestBody UsuarioDto usuarioDto) {
    usuarioService.saveCorrigido(usuarioDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
