package com.app.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.ParseStringDeserializer;

//@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, VoitureConsumer> consumerFactory(){
		JsonDeserializer<VoitureConsumer> deserializer = new JsonDeserializer<>(VoitureConsumer.class);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);
		
		return new DefaultKafkaConsumerFactory<>(consumerConfig(),new ParseStringDeserializer<>(),deserializer);
	}
	
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, VoitureConsumer> kafkaListnerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, VoitureConsumer> factory = new ConcurrentKafkaListenerContainerFactory<String, VoitureConsumer>();
		 factory.setConsumerFactory(consumerFactory());
		 return factory;
	}
	
	
	@Bean
	public Map<String , Object> consumerConfig(){
		JsonDeserializer<VoitureConsumer> deserializer = new JsonDeserializer<>(VoitureConsumer.class);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);
		
		Map<String , Object> map = new HashMap<>();
		
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		map.put(ConsumerConfig.GROUP_ID_CONFIG, "spring_batch");
		map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		map.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "123456");
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.GROUP_ID_CONFIG, "json");
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		map.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "2000");
		 
		return map;
	}
}
