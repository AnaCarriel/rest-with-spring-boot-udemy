package br.com.anacarriel;

import br.com.anacarriel.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageConfig.class
})
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);

		//Para criar uma nova senha seria esse o codigo abaixo
		/*
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result = bCryptPasswordEncoder.encode("admin123");
		System.out.println("My hash" + result);
		*/
	}
}
