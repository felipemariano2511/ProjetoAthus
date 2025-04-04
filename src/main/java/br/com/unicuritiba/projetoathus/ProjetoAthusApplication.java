package br.com.unicuritiba.projetoathus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProjetoAthusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoAthusApplication.class, args);
	}

}
