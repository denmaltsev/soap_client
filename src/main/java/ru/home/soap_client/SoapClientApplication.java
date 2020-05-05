package ru.home.soap_client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.home.soap_client.repository.UserRepository;
import ru.home.springsoap.entity.GetUserListResponse;
import ru.home.springsoap.entity.GetUserResponse;

@SpringBootApplication
public class SoapClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapClientApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup (UserRepository userRepository) {
		return args -> {

			String url = null;
			String userName = null;
			for (String arg : args) {
				if (arg.startsWith("user=")) {
					userName = arg.split("=")[1];
				}
				if (arg.startsWith("url=")) {
					url = arg.split("=")[1];
				}
			}

			if (url != null && !url.isEmpty()) {
				// Получаем всех пользователей
				if (userName == null || userName.isEmpty()) {
					GetUserListResponse response = userRepository.getAllUsers(url);
					System.out.println("Request status: " + response.getStatus());
					if (response.getErrors() != null) {
						response.getErrors().getError().forEach(e -> {
							System.err.println("ERROR code:" + e.getCode());
							System.err.println("ERROR message: " + e.getMessage());
						});
					} else {
						if (response.getUserList() != null) {
							response.getUserList().getUser().forEach(u -> {
								System.out.println("USER name: " + u.getName());
								System.out.println("USER profession: " + u.getProfession());
								System.out.println("USER age: " + u.getAge());
							});
						}
					}
				} else {
					// Получаем данные пользователя по переданному имени
					GetUserResponse response = userRepository.getUser(url, userName);
					System.out.println("Request status: " + response.getStatus());
					if (response.getErrors() != null) {
						response.getErrors().getError().forEach(e -> {
							System.err.println("ERROR code:" + e.getCode());
							System.err.println("ERROR message: " + e.getMessage());
						});
					} else {
						if (response.getUser() != null) {
							System.out.println("USER name: " + response.getUser().getName());
							System.out.println("USER profession: " + response.getUser().getProfession());
							System.out.println("USER age: " + response.getUser().getAge());
						}
					}
				}
			} else {
				System.err.println("URL parameter not passed");
			}
		};
	}

}
