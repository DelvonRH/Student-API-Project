package com.tts.StudentAPI.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tts.StudentAPI.model.Student;
import com.tts.StudentAPI.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/test")
	public String testEndpoint() {
		return "This is test on v1";
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		List<Student> students = studentRepository.findAll();
		return students;
	}
	
	@GetMapping("students/{id}")
	public Optional<Student> getStudentById(@PathVariable(value="id") Long id){
		Optional<Student> student = studentRepository.getStudentById(id);
		return student;
	}
	
	@PostMapping("/students")
	public void createStudent(@RequestBody Student student) {
		studentRepository.save(student);
	}
}
