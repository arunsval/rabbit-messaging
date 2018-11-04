package com.arun.springboot.rabbitmessaging.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class MessagingConfig {

	@Value("${amazon.queue.name}")
	private String amazonQueueName; //queuenames should be in array in future
	@Value("${amazon.exchange.name}")
	private String amazonExchangeName;
	@Value("${amazon.routing.key}")
	private String amazonRouteKey;
	@Value("${walmart.queue.name}")
	private String walmartQueueName;
	@Value("${walmart.exchange.name}")
	private String walmartExchangeName;
	@Value("${walmart.routing.key}")
	private String walmartRoutekey;
	public String getAmazonQueueName() {
		return amazonQueueName;
	}
	public void setAmazonQueueName(String amazonQueueName) {
		this.amazonQueueName = amazonQueueName;
	}
	public String getAmazonExchangeName() {
		return amazonExchangeName;
	}
	public void setAmazonExchangeName(String amazonExchangeName) {
		this.amazonExchangeName = amazonExchangeName;
	}
	public String getAmazonRouteKey() {
		return amazonRouteKey;
	}
	public void setAmazonRouteKey(String amazonRouteKey) {
		this.amazonRouteKey = amazonRouteKey;
	}
	public String getWalmartQueueName() {
		return walmartQueueName;
	}
	public void setWalmartQueueName(String walmartQueueName) {
		this.walmartQueueName = walmartQueueName;
	}
	public String getWalmartExchangeName() {
		return walmartExchangeName;
	}
	public void setWalmartExchangeName(String walmartExchangeName) {
		this.walmartExchangeName = walmartExchangeName;
	}
	public String getWalmartRoutekey() {
		return walmartRoutekey;
	}
	public void setWalmartRoutekey(String walmartRoutekey) {
		this.walmartRoutekey = walmartRoutekey;
	}
	
	
}
