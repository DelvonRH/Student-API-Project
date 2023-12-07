package com.tts.StudentAPI.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tts.StudentAPI.model.Student;
import com.tts.StudentAPI.repository.StudentRepository;

@RestController
@RequestMapping("/api/v2")
public class StudentControllerV2 {

	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/test")
	public String testEndpoint() {
		return "This is test on v2";
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getStudents(@RequestParam(value="lovesClass", required=false) String lovesClass){
		List<Student> students;
		
		if(lovesClass != null && (lovesClass.equals("true") || lovesClass.equals("false"))) {
			boolean loves = Boolean.parseBoolean(lovesClass);
			students = studentRepository.getStudentByLovesClass(loves);
		} else {
			students = studentRepository.findAll();
		}
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
	
	@GetMapping("students/{id}")
	public ResponseEntity<Optional<Student>> getStudentById(@PathVariable(value="id") Long id){
		Optional<Student> student = studentRepository.getStudentById(id);
		if(!student.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@PostMapping("/students")
	public ResponseEntity<Void> createStudent(@RequestBody Student student, BindingResult bindingResult) {
		System.out.println("Binding Result:");
		System.out.println(bindingResult);
		System.out.println("Student Result:");
		System.out.println(student);
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		studentRepository.save(student);
		return new ResponseEntity<>(HttpStatus.CREATED);


	}
	
}
