package com.rollingstone.gstyle;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@OpenAPIDefinition(servers = {@Server(url = "http://3.38.188.126:8001", description = "Default Server URL")})
@SpringBootApplication
public class GStyleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GStyleApplication.class, args);
	}

}
