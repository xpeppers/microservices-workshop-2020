package com.xpeppers.invoices;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InvoicesApplication {

	@Bean
	public Queue hello() {
		return new Queue("hello");
	}

	@Bean
	public PaymentReceiver receiver() {
		return new PaymentReceiver();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(InvoicesApplication.class, args);
	}
}


