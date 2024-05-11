package br.com.unisinos.example.case1.controller;

import br.com.unisinos.example.case1.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/usuario")
@RestController("UsuarioControllerCase1")
public class UsuaioController {

  @Autowired
  UsuarioServiceImpl anuncioCaoService;

  @GetMapping
  public List<String> getAllAnuncios() {
    return List.of();
  }
}
