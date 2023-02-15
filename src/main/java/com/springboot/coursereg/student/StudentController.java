package com.springboot.coursereg.student;

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
@RequestMapping("/api/students")
public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	// Create student REST API
	@PostMapping()
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED); 
	}
	
	// Get all students REST API
	@GetMapping
	public List<Student> getAllStudents() { 
		return studentService.getAllStudents(); 
	}
	
	// Update Student REST API
	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long studentId,@RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.updateStudent(student, studentId), HttpStatus.OK); 
	}
	
	// Update Student, enroll into course REST API
	@PutMapping("{id}/enrollInCourse/{courseId}")
	public ResponseEntity<Student> enrollStudent(@PathVariable("courseId") long courseId,@PathVariable("id") long studentId) {
		return new ResponseEntity<Student>(studentService.enrollStudentIntoCourse(courseId, studentId), HttpStatus.OK); 
	}
	
	// Update Student, unenroll from course REST API
	@PutMapping("{id}/unenrollFromCourse/{courseId}")
	public ResponseEntity<Student> unenrollStudent(@PathVariable("courseId") long courseId,@PathVariable("id") long studentId) {
		return new ResponseEntity<Student>(studentService.unenrollStudentFromCourse(courseId, studentId), HttpStatus.OK); 
	}
	
	// Update Student, unenroll from all courses REST API
	@PutMapping("{id}/unenrollFromAllCourses")
	public ResponseEntity<Student> unenrollStudentFromAllCourses(@PathVariable("id") long studentId) {
		return new ResponseEntity<Student>(studentService.unenrollStudentFromAllCourses(studentId), HttpStatus.OK); 
	}
	
	// Delete Student REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long studentId) {
		studentService.deleteStudent(studentId);; 
		return new ResponseEntity<String>("Student was deleted successfully.", HttpStatus.OK);
	}

}
