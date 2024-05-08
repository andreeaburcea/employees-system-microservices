package com.company.employeesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


//@OpenAPIDefinition(
//		info = @Info(
//				title="EmployeeService REST APIs",
//				description="Employee Service REST APIs Documentation",
//				version="v1.0",
//				contact=@Contact(
//						name="Andreea",
//						email="r.andreeaburcea@gmail.com",
//						url="https your website"
//				),
//				license = @License(
//						name="Apache 3.0",
//						url="/license"
//				)
//		),
//		externalDocs=@ExternalDocumentation(
//				description="Employee Service Doc",
//				url=""
//		)
//)
@SpringBootApplication
//enables component scanning for interfaces that declare they are feign clients.
@EnableFeignClients
//@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class EmployeesServiceApplication {


	// this method returns an instance of the class
	// we need to annotate that method with @Bean annotation
	// now this RestTemplate is registered in a spring container.
//	@Bean
//	public RestTemplate restTemplate() {
//		// returns an instance of RestTemplate class
//		return new RestTemplate();
//	}

//	@Bean
//	public FeignContext feignContext() {
//		// You can customize the creation of FeignContext here if needed
//		return new FeignContext();
//	}
	@Bean
	public WebClient webClient() {
//		to build the instance
		return WebClient.builder().build();
	}


	public static void main(String[] args) {
		SpringApplication.run(EmployeesServiceApplication.class, args);
	}

}
