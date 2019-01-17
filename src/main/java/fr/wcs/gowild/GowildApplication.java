package fr.wcs.gowild;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GowildApplication {

	public static void main(String[] args) {
		SpringApplication.run(GowildApplication.class, args);
	}

}

