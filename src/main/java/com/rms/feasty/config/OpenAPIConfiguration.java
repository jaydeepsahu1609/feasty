/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Value("${metadata.name}")
    private String name;

    @Value("${metadata.email}")
    private String email;

    @Value("${server.port}")
    private String port;

    @Value("${metadata.project-name}")
    private String projectName;

    @Value("${metadata.project-version}")
    private String projectVersion;

    @Value("${metadata.project-description}")
    private String projectDescription;

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:"+port);
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName(name);
        myContact.setEmail(email);

        Info information = new Info()
                .title(projectName)
                .version(projectVersion)
                .description(projectDescription)
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}