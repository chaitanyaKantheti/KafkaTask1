package com.practice.KafkaTask1.controllerTest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.practice.KafkaTask1.controller.Controller;
import com.practice.KafkaTask1.model.Student;
import com.practice.KafkaTask1.services.StudentService;


@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private Controller controller;

	@Mock
	private StudentService studentService;
	
	@Rule public MockitoRule rule=MockitoJUnit.rule();
	
	@Before
	public void setUp() throws Exception{
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
		//mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGreetSuccess() throws Exception{
		/*mockMvc.perform(MockMvcRequestBuilders.get("/greet"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("Hello world"));*/
		
		mockMvc.perform(get("/greet"))
		.andExpect(status().isOk())
		.andExpect(content().string("Hello world"));
	}

	@Test
	public void testGetStudentSuccess() throws Exception{
		Student stu=new Student();
		stu.setsName("chaitanya");
		
		when(studentService.getStudent(1)).thenReturn(stu);
		
		mockMvc.perform(get("/students/1")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.sName").value("chaitanya"))
		.andExpect(jsonPath("$.*",Matchers.hasSize(3)));
	}


	@Test
	public void validate_customerInfo2() throws Exception {
		mockMvc.perform(get("/customer")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.name").value("Jack")).andExpect(jsonPath("$.location").value("US"));
	}
}

