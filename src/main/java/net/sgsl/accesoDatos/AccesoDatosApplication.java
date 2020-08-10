package net.sgsl.accesoDatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AccesoDatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccesoDatosApplication.class, args);
	}

}
