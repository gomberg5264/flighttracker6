package com.nomad.skyscanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan
public class SkyscannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyscannerApplication.class, args);
	}
}
