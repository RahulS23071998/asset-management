package com.assetcircle.assetmanagement;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
		info = @Info(
				title = "Asset Management REST APIs",
				description = "Asset Management REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Asset Management",
						email = "rahul.s@laderatechnology.com",
						url = "https://laderatechnology.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://laderatechnology.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Asset Management Doc",
				url = "https://laderatechnology.com"
		)
)
public class AssetManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetManagementApplication.class, args);
	}

}
