package es.soprasteria.formacion.configuration;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("es.soprasteria.formacion"))
        .paths(PathSelectors.any()).build().securityContexts(Arrays.asList(securityContext()))
        .securitySchemes(Arrays.asList(basicAuthScheme()));
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(Arrays.asList(basicAuthReference()))
        .forPaths(PathSelectors.ant("/v1/profile/**"))
        .build();
  }

  private SecurityReference basicAuthReference() {
    return new SecurityReference("basicAuth", new AuthorizationScope[0]);
  }

  private SecurityScheme basicAuthScheme() {
    return new BasicAuth("basicAuth");}
}
