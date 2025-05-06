package com.jfl.shopping_list;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ShoppingListApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ShoppingListApplication.class, args);
	}

	@Component
	class ApiUrlLogger implements ApplicationRunner {

		private final Environment env;

		public ApiUrlLogger(Environment env) {
			this.env = env;
		}

		@Override
		public void run(ApplicationArguments args) {
			String port = env.getProperty("server.port", "8080");
			String contextPath = env.getProperty("server.servlet.context-path", "");
			String url = "http://localhost:" + port + contextPath;

			System.out.println("==========================================\n");
			System.out.println("🌐 API available on: " + url);
			System.out.println("==========================================\n");
		}
	}

}
