package com.el_regreso.lista_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.el_regreso.lista_usuarios")
public class ListaUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListaUsuariosApplication.class, args);
	}
}