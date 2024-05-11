package br.com.unisinos.example.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI testOpenAPIDefinition() {

    return new OpenAPI()
            .info(new Info().title("Estudo de segurança em Código Fonte: " +
                    "Uso de sistemas beaseados em senhas fracas"
            )
            .description("Aplicação desenvolvida para exemplos com casos de teste: <br><li>Vulneráveis<br><li>Corrigidos.")
            .version("1.0.0-rc.1"))
            .externalDocs(new ExternalDocumentation().description("Documentação")
                    .url("https://github.com/lzanardii"));
  }
}
