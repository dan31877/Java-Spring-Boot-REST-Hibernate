package com.springboot.coursereg.teacher;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = {"http://localhost:3000"}) - To work locally with React/Angular
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	
	private TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		super();
		this.teacherService = teacherService;
	} 
	
	// Create teacher REST API
	@PostMapping()
	public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher) {
		return new ResponseEntity<Teacher>(teacherService.saveTeacher(teacher), HttpStatus.CREATED); 
	}
	
	// Get all teachers REST API
	@GetMapping
	public List<Teacher> getAllTeachers() { 
		return teacherService.getAllTeachers(); 
	}
	
	// Update teacher REST API
	@PutMapping("{id}")
	public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") long teacherId,@RequestBody Teacher teacher) {
		return new ResponseEntity<Teacher>(teacherService.updateTeacher(teacher, teacherId), HttpStatus.OK); 
	}
	
	// Update teacher REST API
	@PutMapping("{id}/removeTeacherFromAllCourses")
	public ResponseEntity<Teacher> removeTeacherFromAllCourses(@PathVariable("id") long teacherId) {
		return new ResponseEntity<Teacher>(teacherService.removeTeacherFromAllCourses(teacherId), HttpStatus.OK); 
	}
	
	// Delete teacher REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTeacher(@PathVariable("id") long teacherId) {
		teacherService.deleteTeacher(teacherId);
		return new ResponseEntity<String>("Teacher was deleted successfully.", HttpStatus.OK);
	}

}
