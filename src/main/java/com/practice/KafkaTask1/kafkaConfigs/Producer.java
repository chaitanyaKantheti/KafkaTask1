package com.practice.KafkaTask1.kafkaConfigs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.practice.KafkaTask1.model.Student;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;



@Configuration
public class Producer {
	String groupId="new-group";
	
	@Bean
	public Map<String,Object> producerConfiguration(){
		Map<String,Object> props=new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
	    props.put(ProducerConfig.ACKS_CONFIG,"all");
		return props;
	}
	
	@Bean
	public ProducerFactory<String, Student> producerFactory(){
		return new DefaultKafkaProducerFactory<>(producerConfiguration());
	}
	
	@Bean
	public KafkaTemplate<String, Student> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
	
}
