package com.practice.KafkaTask1.daoLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.practice.KafkaTask1.model.Branch;
import com.practice.KafkaTask1.model.Hod;
import com.practice.KafkaTask1.model.Student;



@Component
public class StoringDAOImpl implements StoringDAO{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSourse;

	@Override
	public int getHodId(String hodName) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		int hodId=0;
		
		try {
			connection=this.dataSourse.getConnection();
			ps=connection.prepareStatement("insert into hod(hodName)values('"+hodName+"')");
			ps.executeQuery();
		}
		catch(SQLException e){
			System.out.println(e);
		}

		
		try {
			connection=this.dataSourse.getConnection();
			ps=connection.prepareStatement("select hodId from hod where '"+hodName +"'=hod.hodName");
			rs=ps.executeQuery();
		}
		catch(SQLException e){

			System.out.println(e);
		}
		
		
		try {
			if(rs.next()) {
				hodId=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hodId;
	}
	
	
	@Override
	public Student getStudentData(int sId) {
		System.out.println("inside the dao layer");
		
		//List<Student> studentInfo=new ArrayList<Student>();
		
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		Student student=new Student();
		
		try {
			connection=this.dataSourse.getConnection();
			ps=connection.prepareStatement("SELECT student.sName,student.sId,branch.bName,hod.hodName FROM ((branch INNER JOIN hod ON branch.hodId=hod.hodId) INNER JOIN student ON branch.bId=student.bId) WHERE student.sId='"+sId+"'");
			rs=ps.executeQuery();
		     System.out.println("result set"+rs);
			while (rs.next()) {
				student.setsName(rs.getString("sName"));
				student.setsID(rs.getInt("sId"));
			}
		}
		catch(SQLException e){

			System.out.println(e);
		}
		
		return student;
	}

	@Override
	public List<Student> getStudents() {
		
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		List<Student> students=new ArrayList<>();
		
		try {
			connection=this.dataSourse.getConnection();
			ps=connection.prepareStatement("SELECT s.sId,s.sName,b.bName,b.bId FROM student s,branch b WHERE s.bId=b.bId");
			rs=ps.executeQuery();
		     System.out.println("result set"+rs);
			while (rs.next()) {
				Student stu=new Student();
				stu.setsID(rs.getInt("sId"));
				stu.setsName(rs.getString("sName"));
				Branch bch=new Branch();
				bch.setbId(rs.getInt("bId"));
				bch.setbName(rs.getString("bName"));
				stu.setBranch(bch);
				students.add(stu);
			}
		}
		catch(SQLException e){

			System.out.println(e);
		}
		
		return students;
	}

}