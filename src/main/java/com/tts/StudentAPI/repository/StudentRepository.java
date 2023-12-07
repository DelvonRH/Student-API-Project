package com.tts.StudentAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.StudentAPI.model.Student;
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

	Optional<Student> getStudentById(Long id);
	
	List<Student> getStudentByLovesClass(boolean loves);
	
	@Override
	List<Student> findAll();

	
}
