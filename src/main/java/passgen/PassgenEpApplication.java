package passgen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import passgen.controllers.PassgenController;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}, scanBasePackageClasses = PassgenController.class)
public class PassgenEpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassgenEpApplication.class, args);
	}

}
