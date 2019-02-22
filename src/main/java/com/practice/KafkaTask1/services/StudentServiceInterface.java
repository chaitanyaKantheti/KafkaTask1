package com.practice.KafkaTask1.services;

import java.util.List;

import com.practice.KafkaTask1.model.Student;

public interface StudentServiceInterface {
	public void sendDataToKafka(Student student);
	public void storeDataToHod(String hodName);
	public List<Student> getStudents();
	public Student getStudent(int sId);

}
