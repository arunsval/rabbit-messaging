package com.arun.springboot.rabbitmessaging;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.arun.springboot.rabbitmessaging.config.MessagingConfig;

@EnableRabbit
@SpringBootApplication
public class RabbitMessagingApplication extends SpringBootServletInitializer
{
	@Autowired
	private MessagingConfig consumerConfig;
	
	public MessagingConfig getMessagingConfig() {
		return consumerConfig;
	}

	public void setMessagingConfig(MessagingConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
	}
	
	@Bean
	public MessagingConfig consumerConfig()
	{
		return new MessagingConfig();
	}
	
	//Get Amazon Queue
	@Bean
	public Queue getAmazonQueue()
	{
		return new Queue(getMessagingConfig().getAmazonQueueName(), false);
	}
	//Get Amazon Exchange
	@Bean
	public TopicExchange getAmazonExchange()
	{
		return new TopicExchange(getMessagingConfig().getAmazonExchangeName());
	}
	//Bind Amazon queue and exchange
	@Bean
	public Binding declareAmazonBinding()
	{
		return BindingBuilder.bind(getAmazonQueue()).to(getAmazonExchange()).with(getMessagingConfig().getAmazonRouteKey());
	}
	
	
	//Get Walmart Queue
		@Bean
		public Queue getWalmartQueue()
		{
			return new Queue(getMessagingConfig().getWalmartQueueName(), false);
		}
		//Get Walmart Exchange
		@Bean
		public TopicExchange getWalmartExchange()
		{
			return new TopicExchange(getMessagingConfig().getWalmartExchangeName());
		}
		//Bind Walmart queue and exchange
		@Bean
		public Binding declareWalmartBinding()
		{
			return BindingBuilder.bind(getWalmartQueue()).to(getWalmartExchange()).with(getMessagingConfig().getWalmartRoutekey());
		}
		
		@Bean
		public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
		{
			RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
			rabbitTemplate.setMessageConverter(producerJackson2JsonMessageConvertor());
			return rabbitTemplate;
		}
		
		@Bean
		public Jackson2JsonMessageConverter producerJackson2JsonMessageConvertor()
		{
			return new Jackson2JsonMessageConverter();
		}
		
		
	public static void main(String[] args) {
		SpringApplication.run(RabbitMessagingApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(RabbitMessagingApplication.class);
	}	


	
}
