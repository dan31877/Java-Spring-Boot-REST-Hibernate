package com.springboot.coursereg.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.coursereg.course.Course;
import com.springboot.coursereg.course.CourseRepository;
import com.springboot.coursereg.exception.ResourceNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(long id) {
		return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
	}

	@Override
	public Student updateStudent(Student student, long id) {
		// Check if student is in DB
		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
		
		// Set existingStudent params to new student params
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		// Save existing student to DB
		studentRepository.save(existingStudent);
		return existingStudent;
	}
	
	

	@Override
	public void deleteStudent(long id) {
		// Check if student is in DB
		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
		
		// Remove student from all enrolled courses
		existingStudent.unenrollStudentFromAllCourses();
		
		// Delete existing student from DB
		studentRepository.deleteById(id);
	}
	
	@Override
	public Student enrollStudentIntoCourse(long courseId, long studentId) {
		// Check if course and student are in DB
		Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
		Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
		
		// Remove student from the course
		existingStudent.enrollStudentIntoCourse(existingCourse);
		
		// Save existing student to DB
		studentRepository.save(existingStudent);
		return existingStudent;
	}
	
	@Override
	public Student unenrollStudentFromCourse(long courseId, long studentId) {
		// Check if course and student are in DB
		Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
		Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
		
		// Remove student from the course
		existingStudent.unenrollStudentFromCourse(existingCourse);
		
		// Save existing student to DB
		studentRepository.save(existingStudent);
		return existingStudent;
	}
	
	@Override
	public Student unenrollStudentFromAllCourses(long studentId) {
		// Check if course and student are in DB
		Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
		
		// Remove student from all enrolled courses
		existingStudent.unenrollStudentFromAllCourses();
		
		// Save existing student to DB
		studentRepository.save(existingStudent);
		return existingStudent;
	}

}
