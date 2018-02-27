package me.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import me.learning.controller.ConsumerControllerClient;

@SpringBootApplication
public class EmployeeConsumerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(EmployeeConsumerApplication.class, args);
		ConsumerControllerClient client=ctx.getBean(ConsumerControllerClient.class);
		System.out.println(client);
		client.getEmployee();
		
	}
	@Bean
	public ConsumerControllerClient getConsumerControllerClient() {
		return new ConsumerControllerClient();
	}
}
