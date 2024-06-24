package webly.meyzieu_gym.back;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Application started, ride now!");
	}

	@PostConstruct
	public void init() {
		System.out.println("DB_URL: " + System.getenv("DB_URL"));
		System.out.println("DB_USER: " + System.getenv("DB_USER"));
		System.out.println("DB_PASSWORD: " + System.getenv("DB_PASSWORD"));
		System.out.println("SPRING_PROFILES_ACTIVE: " + System.getenv("SPRING_PROFILES_ACTIVE"));
		System.out.println("JWT_COOKIE_NAME: " + System.getenv("JWT_COOKIE_NAME"));
		System.out.println("JWT_SECRET: " + System.getenv("JWT_SECRET"));
	}

}
