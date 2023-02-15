package com.springboot.coursereg.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	} 
	
	// Create course REST API
	@PostMapping()
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
		return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED); 
	}
	
	// Get all courses REST API
	@GetMapping
	public List<Course> getAllCourses() { 
		return courseService.getAllCourses(); 
	}
	
	// Update course REST API
	@PutMapping("{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable("id") long courseId,@RequestBody Course course) {
		return new ResponseEntity<Course>(courseService.updateCourse(course, courseId), HttpStatus.OK); 
	}
	
	// Update course, enroll student REST API
	@PutMapping("{id}/enrollStudent/{studentId}")
	public ResponseEntity<Course> enrollStudent(@PathVariable("id") long courseId,@PathVariable("studentId") long studentId) {
		return new ResponseEntity<Course>(courseService.enrollStudent(courseId, studentId), HttpStatus.OK); 
	}
	
	// Update course, unenroll student REST API
	@PutMapping("{id}/unenrollStudent/{studentId}")
	public ResponseEntity<Course> unenrollStudent(@PathVariable("id") long courseId,@PathVariable("studentId") long studentId) {
		return new ResponseEntity<Course>(courseService.unenrollStudent(courseId, studentId), HttpStatus.OK); 
	}
	
	// Update course, unenroll all students REST API
	@PutMapping("{id}/unenrollStudents")
	public ResponseEntity<Course> unenrollAllStudents(@PathVariable("id") long courseId) {
		return new ResponseEntity<Course>(courseService.unenrollAllStudents(courseId), HttpStatus.OK); 
	}
	
	// Update course, assign teacher REST API
	@PutMapping("{id}/assignTeacher/{teacherId}")
	public ResponseEntity<Course> assignTeacherToCourse(@PathVariable("id") long courseId,@PathVariable("teacherId") long teacherId) {
		return new ResponseEntity<Course>(courseService.assignTeacherToCourse(courseId, teacherId), HttpStatus.OK); 
	}
	
	// Update course, remove teacher REST API
	@PutMapping("removeTeacher/{id}")
	public ResponseEntity<Course> removeTeacherFromCourse(@PathVariable("id") long courseId) {
		return new ResponseEntity<Course>(courseService.removeTeacher(courseId), HttpStatus.OK); 
	}
	
	// Delete course REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable("id") long courseId) {
		courseService.deleteCourse(courseId);;
		return new ResponseEntity<String>("Course was deleted successfully.", HttpStatus.OK);
	}
}
