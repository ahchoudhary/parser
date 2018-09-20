package com.ef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ParserApplication {


	public static void main(String[] args) {

		SpringApplication.run(ParserApplication.class, args);
	}
	/*@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			//String[] beanNames = ctx.getBeanDefinitionNames();

			logFileProcessor.processFile(args[0]);
		};
	}*/
}
