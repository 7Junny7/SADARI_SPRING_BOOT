package co.soft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("co.soft.*")
public class SadariSpringBoot1Application {

	public static void main(String[] args) {
		SpringApplication.run(SadariSpringBoot1Application.class, args);
	}

}
