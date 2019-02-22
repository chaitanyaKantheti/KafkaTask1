package com.practice.KafkaTask1.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.KafkaTask1.model.Customer;
import com.practice.KafkaTask1.model.Student;
import com.practice.KafkaTask1.services.StudentServiceInterface;

@RestController
public class Controller {

	@Autowired
	private StudentServiceInterface studentServiceinteface;
	
	@Autowired
	KafkaTemplate<String,Student> kafkaTemplate;

	@RequestMapping(value="/greet")
	public String greet() {
		return("Hello world");
	}

	@RequestMapping(method=RequestMethod.GET,value="/getStudents",produces=MediaType.APPLICATION_JSON)
	public List<Student> getStudents(){
		return studentServiceinteface.getStudents();
	}

	@RequestMapping(method=RequestMethod.POST,value="/sendData",consumes=MediaType.APPLICATION_JSON)
	public void sendData(@RequestBody Student student) {
		studentServiceinteface.sendDataToKafka(student);	
	}

	@RequestMapping(method=RequestMethod.GET,value="/students/{sId}",produces="application/json;charset=UTF-8")
	public Student getStudent(@PathVariable int sId){
		return studentServiceinteface.getStudent(sId);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public Customer getAddress() { 
		Customer customer = new Customer();
		customer.setName("Jack");
		customer.setLocation("US");
		return customer;
	}
}
