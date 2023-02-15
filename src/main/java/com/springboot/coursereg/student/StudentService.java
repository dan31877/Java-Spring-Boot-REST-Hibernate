package com.springboot.coursereg.student;

import java.util.List;

import com.springboot.coursereg.course.Course;

public interface StudentService {
	
	Student saveStudent(Student student);
	List<Student> getAllStudents(); 
	Student getStudentById(long id); 
	Student updateStudent(Student student, long id); 
	void deleteStudent(long id);
	Student unenrollStudentFromCourse(long courseId, long studentId);
	Student unenrollStudentFromAllCourses(long studentId);
	Student enrollStudentIntoCourse(long courseId, long studentId);

}
