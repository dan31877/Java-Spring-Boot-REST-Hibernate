package com.springboot.coursereg.course;

import java.util.List;

public interface CourseService {
	
	Course saveCourse(Course course);
	List<Course> getAllCourses(); 
	Course getCourseById(long id); 
	Course updateCourse(Course course, long id); 
	Course enrollStudent(long courseId, long studentId);
	Course assignTeacherToCourse(long courseId, long teacherId);
	Course removeTeacher(long courseId); 
	Course unenrollStudent(long courseId, long studentId); 
	Course unenrollAllStudents(long courseId); 
	void deleteCourse(long id);

}
