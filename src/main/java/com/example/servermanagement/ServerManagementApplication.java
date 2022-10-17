package com.example.servermanagement;

import com.example.servermanagement.model.Server;
import com.example.servermanagement.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.servermanagement.enumeration.Status.SERVER_DOWN;
import static com.example.servermanagement.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class

ServerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Pc Pessoal", "http://localhost:8080/server/image/server1.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.58", "Fedora Linux", "16 GB", "Optiplex","http://localhost:8080/server/image/server2.png", SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.21", "MS 2008", "32 GB", "Servidor Web", "http://localhost:8080/server/image/server3.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.14", "Red Hat Enterprise Linux", "64 GB", "Mail Server", "http://localhost:8080/server/image/server4.png", SERVER_DOWN));
		};
	}

}
