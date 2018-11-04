package com.arun.springboot.rabbitmessaging.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.arun.springboot.rabbitmessaging.config.MessagingConfig;
import com.arun.springboot.rabbitmessaging.resource.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Producer  implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
	
	private MessagingConfig messagingConfig;
	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public Producer(final RabbitTemplate rabbitTemplate)
	{
		this.rabbitTemplate = rabbitTemplate;
	}
	
	

	public MessagingConfig getMessagingConfig() {
		return messagingConfig;
	}


	@Autowired
	public void setMessagingConfig(MessagingConfig messagingConfig) {
		this.messagingConfig = messagingConfig;
	}

	

	@Override
	public void run(String... args) throws Exception {
		String amazonExchangeName = getMessagingConfig().getAmazonExchangeName();
		String amazonQueueName = getMessagingConfig().getAmazonQueueName();
		String amazonRouteKey = getMessagingConfig().getAmazonRouteKey();
		
		
		//Send alert to amazon
		List<Product> productsList  = new ArrayList<Product>();
		Product luckySmartWatch = new Product("gearPro", Long.valueOf(100), BigDecimal.valueOf(10.5), BigDecimal.valueOf(90));
		Product luckyTV = new Product("smartTv", Long.valueOf(50), BigDecimal.valueOf(100.5), BigDecimal.valueOf(400));
		productsList.add(luckyTV);
		productsList.add(luckySmartWatch);
		
		//iterate products
		productsList.forEach(product ->{
			LOGGER.info("Adding product {} to Exchange {}", product.getName(), amazonExchangeName);
			this.rabbitTemplate.convertAndSend(amazonExchangeName, amazonRouteKey, product);
		});
		
	}
}
