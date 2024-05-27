package uz.pdp.g34springbootsecurity.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@OpenAPIDefinition(
//        info = @Info(
//                title = "G34 Spring boot security",
//                description = """
//                            This project is designed as a hands-on learning experience for
//                            understanding the integration of Spring Security and OpenAPI
//                            in a Spring Boot application. The main objectives are to grasp
//                            the fundamental concepts of securing Spring Boot applications and
//                            documenting APIs using OpenAPI specifications.
//                        """,
//                termsOfService = "https://pdp.uz",
//                contact = @Contact(
//                        name = "John Doe",
//                        email = "john_doe@gmail.com"
//                ),
//                license = @License(
//                        name = "Apache 2.0",
//                        url = "https://apache.com"
//                ),
//                version = "${spring.application.version}",
//                summary = """
//                        This project is designed to teach the integration of
//                        Spring Security and OpenAPI in a Spring Boot application.
//                        Key features include user authentication and authorization,
//                        role-based access control, security configurations,
//                        and API documentation with OpenAPI and Swagger UI.
//                        """
//        ),
//        servers = {
//                @Server(
//                        url = "http://localhost:8080",
//                        description = "LOCAL"
//                ),
//                @Server(
//                        url = "https://devspring.sec-g34.uz",
//                        description = "DEVELOPMENT"
//                ),
//                @Server(
//                        url = "https://spring.sec-g34.uz",
//                        description = "PRODUCTION"
//                )
//        }
//)
@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("G34 Spring boot security")
                        .description(
                                """
                                        This project is designed as a hands-on learning experience for
                                        understanding the integration of Spring Security and OpenAPI\s
                                        in a Spring Boot application. The main objectives are to grasp\s
                                        the fundamental concepts of securing Spring Boot applications and\s
                                        documenting APIs using OpenAPI specifications.
                                        """
                        )
                        .version("0.0.1")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("http://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                        .contact(
                                new Contact()
                                        .name("John Doe")
                                        .email("john.doe@gmail.com")
                        )
                        .termsOfService("http://www.google.com")
                )
                .servers(
                        List.of(
                                new Server().url("http://localhost:8080").description("LOCAL"),
                                new Server().url("http://localhost:8081").description("DEVELOPMENT"),
                                new Server().url("http://localhost:8082").description("PRODUCTION")
                        )
                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Bearer Authentication")
                )
                .components(
                        new Components()
                                .addSecuritySchemes("Bearer Authentication", bearerSecurityScheme())
                );
    }

    @Bean
    public SecurityScheme bearerSecurityScheme() {
        return new SecurityScheme()
                .name("Bearer Authentication")
                .description("Bearer authentication for sending requests within header")
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}