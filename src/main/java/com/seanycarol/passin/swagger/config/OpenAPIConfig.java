package com.seanycarol.passin.swagger.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("seany.caroliny@gmail.com");
        contact.setName("Seany Caroliny");
        contact.setUrl("https://github.com/seanycarol");

        Info info = new Info()
                .title("Pass.in API")
                .version("1.0")
                .contact(contact)
                .description("Especificações da API para o back-end da aplicação pass.in construída durante o NLW Unite Java da Rocketseat.");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
