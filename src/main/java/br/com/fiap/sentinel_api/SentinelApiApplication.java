package br.com.fiap.sentinel_api;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SentinelApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelApiApplication.class, args);
	}

}
