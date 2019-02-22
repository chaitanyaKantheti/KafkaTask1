package com.practice.KafkaTask1.daoLayer;


import java.sql.SQLException;
import java.util.List;

import com.practice.KafkaTask1.model.Student;

public interface StoringDAO {
	public int getHodId(String hodName) ;
	public Student getStudentData(int sId);
	public List<Student> getStudents();
}
