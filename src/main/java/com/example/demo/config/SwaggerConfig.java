package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Use http because your app runs on Tomcat port 9001 (http) and is exposed via this URL.
                // If the portal gives a different URL, replace it here.
                .servers(List.of(
                        new Server().url("http://9234.408procr.amypo.ai/")
                ));
    }
}
