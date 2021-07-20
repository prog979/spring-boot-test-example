package com.springbootest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.springbootest.dto.Student;
import com.springbootest.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testGetExample() throws Exception {
		List<Student> students = new ArrayList<>();
		Student student = new Student();
		student.setId(1);
		student.setName("Helg");
		students.add(student);
		Mockito.when(studentService.getStudents()).thenReturn(students);
		mockMvc.perform(get("/getMapping")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].name", Matchers.equalTo("Helg")));
	}

	@Test
	public void testPostExample() throws Exception {
		Student student = new Student();
		student.setId(1);
		student.setName("Helg");
		Mockito.when(studentService.saveStudent(ArgumentMatchers.any())).thenReturn(student);
		String json = mapper.writeValueAsString(student);
		mockMvc.perform(post("/postMapping").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
				.andExpect(jsonPath("$.name", Matchers.equalTo("Helg")));
	}

	@Test
	public void testPutExample() throws Exception {
		Student student = new Student();
		student.setId(2);
		student.setName("Matter");
		Mockito.when(studentService.updateStudent(ArgumentMatchers.any())).thenReturn(student);
		String json = mapper.writeValueAsString(student);
		mockMvc.perform(put("/putMapping").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.equalTo(2)))
				.andExpect(jsonPath("$.name", Matchers.equalTo("Matter")));
	}

	@Test
	public void testDeleteExample() throws Exception {
		Mockito.when(studentService.deleteStudent(ArgumentMatchers.anyString())).thenReturn("Student is deleted");
		MvcResult requestResult = mockMvc.perform(delete("/deleteMapping").param("student-id", "1"))
				.andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertEquals(result, "Student is deleted");
	}
}