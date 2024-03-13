package me.pianorang.esdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class EsdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsdemoApplication.class, args);
	}

}
