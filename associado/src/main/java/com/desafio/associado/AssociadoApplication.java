package com.desafio.associado;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssociadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssociadoApplication.class, args);
	}

//	@Bean
//	public CPFValidator cpfValidator() {
//		return new CPFValidator();
//	}
//
//	@Bean
//	public CNPJValidator cnpjValidator() {
//		return new CNPJValidator();
//	}
}
