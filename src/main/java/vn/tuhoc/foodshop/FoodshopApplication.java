package vn.tuhoc.foodshop;	

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class FoodshopApplication {
	public static void main(String[] args) {
		SpringApplication.run(FoodshopApplication.class, args);
	}
}
