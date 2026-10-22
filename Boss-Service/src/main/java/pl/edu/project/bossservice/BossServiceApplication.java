package pl.edu.project.bossservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BossServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BossServiceApplication.class, args);
	}

}
