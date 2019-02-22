package com.practice.KafkaTask1.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.practice.KafkaTask1.daoLayer.StoringDAO;
import com.practice.KafkaTask1.daoLayer.StoringDAOImpl;
import com.practice.KafkaTask1.model.Student;

@Service
public class StudentService implements StudentServiceInterface {
	
	@Autowired
	KafkaTemplate<String, Student> kafkaTemplate;
	
	@Autowired
	StoringDAO storingDAO;
	
	List<Student> students=new ArrayList<>();
	
	public List<Student> getStudents() {
		return storingDAO.getStudents();
	}
	
	@Override
	public void sendDataToKafka(Student student) {
		students.add(student);
		String topic = "new-topic";
		try {
			kafkaTemplate.send(topic, student);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void storeDataToHod(String hodName) {
		storingDAO.getHodId(hodName);
	}

	@Override
	public Student getStudent(int sId) {
		System.out.println("inside the service layer");
		return storingDAO.getStudentData(sId);
	}
	
}
