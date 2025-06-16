package dev.mordechai.studentcoursemanager.config;

import dev.mordechai.studentcoursemanager.documentation.AuthApiDocumentation;
import dev.mordechai.studentcoursemanager.documentation.DtoDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Course Manager API")
                        .description("API for managing students, courses, and registrations")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Mordechai David")
                                .email("mordechai.david@example.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .addSecurityItem(new SecurityRequirement().addList("Session-Key"))
                .schemaRequirement("Session-Key", new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name("Session-Key")
                        .description("Authentication token obtained from login"));
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("Authentication")
                .pathsToMatch("/api/auth/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.addTagsItem(new io.swagger.v3.oas.models.tags.Tag()
                            .name("Authentication")
                            .description("Authentication management APIs"));
                })
                .build();
    }
} 