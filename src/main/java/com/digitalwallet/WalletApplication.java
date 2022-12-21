package com.digitalwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WalletApplication {
	//application is working properly
	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}
}
