package com.practice.KafkaTask1.kafkaListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Component;

import com.practice.KafkaTask1.model.Student;
import com.practice.KafkaTask1.services.StudentService;
import com.practice.KafkaTask1.services.StudentServiceInterface;

@Component
public class Listener {
	
	@Autowired
	StudentServiceInterface studentService;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@KafkaListener(topics="new-topic")
	public void consume(Student student) {
		//int id;
		//String name;
		String hodName=student.getBranch().getHod().getHodName();
		studentService.storeDataToHod(hodName);
		
		//jdbcTemplate.execute("insert into hod(hodName)values('"+student.getBranch().getHod().getHodName()+"')");	
		//jdbcTemplate.execute("insert into branch(bName,hodId)values('"+student.getBranch().getbName()+"','"+student.getBranch().getHod().getHodId()+"')");
		//jdbcTemplate.execute("insert into student(sName,bId)values('"+student.getsName()+"','"+student.getBranch().getbId()+"')");
	}
}
