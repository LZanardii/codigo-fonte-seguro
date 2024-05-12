package br.com.unisinos.example.case1.service;

import br.com.unisinos.example.dto.UsuarioDto;
import br.com.unisinos.example.exception.SenhaFracaException;
import br.com.unisinos.example.model.UsuarioModel;
import br.com.unisinos.example.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

  @InjectMocks
  UsuarioServiceImpl usuarioService;

  @Mock
  UsuarioRepository usuarioRepository;

  @Test
  void deveValidarSenha_erros_todos(){
    UsuarioDto usuarioMock = new UsuarioDto();
    usuarioMock.setSenha("");

    SenhaFracaException thown = assertThrows(SenhaFracaException.class, () -> usuarioService.saveCorrigido(usuarioMock));

    assertEquals(5, thown.getMessages().size());
  }

  @Test
  void deveValidarSenha_erros_size(){
    UsuarioDto usuarioMock = new UsuarioDto();
    usuarioMock.setSenha("@L12l");

    SenhaFracaException thown = assertThrows(SenhaFracaException.class, () -> usuarioService.saveCorrigido(usuarioMock));

    assertEquals(1, thown.getMessages().size());

    Optional<String> firstElement = thown.getMessages().stream().findFirst();
    assertEquals("Deve possuir ao menos 8 caracteres", firstElement.get());
  }

  @Test
  void deveValidarSenha_erros_min_2_numeros_com_1(){
    UsuarioDto usuarioMock = new UsuarioDto();
    usuarioMock.setSenha("lll@io!L1");

    SenhaFracaException thown = assertThrows(SenhaFracaException.class, () -> usuarioService.saveCorrigido(usuarioMock));

    assertEquals(1, thown.getMessages().size());

    Optional<String> firstElement = thown.getMessages().stream().findFirst();
    assertEquals("Deve possuir ao menos 2 caracteres numéricos", firstElement.get());
  }

  @Test
  void deveValidarSenha_erros_min_1_caractere_especial(){
    UsuarioDto usuarioMock = new UsuarioDto();
    usuarioMock.setSenha("lllllL12");

    SenhaFracaException thown = assertThrows(SenhaFracaException.class, () -> usuarioService.saveCorrigido(usuarioMock));

    assertEquals(1, thown.getMessages().size());

    Optional<String> firstElement = thown.getMessages().stream().findFirst();
    assertEquals("Deve possuir ao menos 1 caractere especial", firstElement.get());
  }

  @Test
  void deveValidarSenha_erros_min_1_caractere_maiusculo(){
    UsuarioDto usuarioMock = new UsuarioDto();
    usuarioMock.setSenha("lllll@12");

    SenhaFracaException thown = assertThrows(SenhaFracaException.class, () -> usuarioService.saveCorrigido(usuarioMock));

    assertEquals(1, thown.getMessages().size());

    Optional<String> firstElement = thown.getMessages().stream().findFirst();
    assertEquals("Deve possuir ao menos 1 letra maiúscula", firstElement.get());
  }

  @Test
  void deveValidarSenha_erros_min_1_caractere_minusculo(){
    UsuarioDto usuarioMock = new UsuarioDto();
    usuarioMock.setSenha("LLLLL@12");

    SenhaFracaException thown = assertThrows(SenhaFracaException.class, () -> usuarioService.saveCorrigido(usuarioMock));

    assertEquals(1, thown.getMessages().size());

    Optional<String> firstElement = thown.getMessages().stream().findFirst();
    assertEquals("Deve possuir ao menos 1 letra minúscula", firstElement.get());
  }

  @Test
  void deveValidarSenha(){
    when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(new UsuarioModel());

    UsuarioDto usuarioMock = new UsuarioDto();
    usuarioMock.setNome("leo");
    usuarioMock.setSenha("LlLLl@12");
    usuarioMock.setDataNascimento(LocalDate.of(2002, 8, 10));

    usuarioService.saveCorrigido(usuarioMock);
  }

}
