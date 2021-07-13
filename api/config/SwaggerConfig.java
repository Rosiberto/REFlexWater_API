package com.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	@Bean
	public Docket reflexwaterApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.api.controller"))
		//.apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.any())
		.build()
		.apiInfo(infoAPI());
	}
	
	private ApiInfo infoAPI() {
		return new ApiInfoBuilder()
				.title("REFlex Water Framework")
				.description("REFlex Water - é um framework de IoT desenvolvido para o gerenciamento inteligente de sistemas de abastecimento e distribuição de água.")
				.contact(new Contact("Rosiberto Santos", null, "rsg2@cin.ufpe.br"))
				.version("2021.4.0.9")
				//.license("Apache License Version 2.0")
				//.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}
	
}
