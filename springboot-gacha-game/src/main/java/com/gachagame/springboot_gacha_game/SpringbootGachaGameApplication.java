package com.gachagame.springboot_gacha_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories // Ensures that repositories are enabled
public class SpringbootGachaGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootGachaGameApplication.class, args);
		
	}

}
