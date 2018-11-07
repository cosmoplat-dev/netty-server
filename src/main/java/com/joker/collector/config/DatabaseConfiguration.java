package com.joker.collector.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("com.joker.collector.mapper")
@Import(value = MongoAutoConfiguration.class)
public class DatabaseConfiguration extends AbstractMongoConfiguration {

	@Autowired
	private MongoClient mongoClient;

	@Autowired
	private MongoProperties mongoProperties;

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
		return new ValidatingMongoEventListener(validator());
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

	@Override
	protected String getDatabaseName() {
		return mongoProperties.getDatabase();
	}

	@Override
	public MongoClient mongoClient() {
		return mongoClient;
	}
}
