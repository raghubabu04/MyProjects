package com.toyota.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class ActiveMQStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveMQStarterApplication.class, args);

		// JmsTemplate jms = ctx.getBean(JmsTemplate.class);
		// jms.convertAndSend("javainuse", "test message");
	}
}
