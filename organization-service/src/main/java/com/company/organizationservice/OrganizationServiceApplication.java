package com.company.organizationservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Organization Service REST APIs",
				description = "Organization Service REST APIs Documentation",
				version = "v2.0",
				contact = @Contact(
						name = "Andreea",
						email = "r.andreeaburcea@gmail.com",
						url = "https:// your website if you have"
				),
				license = @License(
						name = "Apache 3.0",
						url ="website/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Organization Service Documentation",
				url = "organization_service.html"
		)

)
@SpringBootApplication
public class OrganizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}
