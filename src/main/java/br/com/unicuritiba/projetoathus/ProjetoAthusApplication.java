package br.com.unicuritiba.projetoathus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ProjetoAthusApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoAthusApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProjetoAthusApplication.class);
	}
	
}
