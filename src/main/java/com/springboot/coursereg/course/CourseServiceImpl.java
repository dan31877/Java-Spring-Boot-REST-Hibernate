package com.springboot.coursereg.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.coursereg.exception.ResourceNotFoundException;
import com.springboot.coursereg.student.Student;
import com.springboot.coursereg.student.StudentRepository;
import com.springboot.coursereg.teacher.Teacher;
import com.springboot.coursereg.teacher.TeacherRepository;


@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository; 
	
	@Autowired
	private StudentRepository studentRepository; 
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	public CourseServiceImpl() {
		super();
	}
	
	public CourseServiceImpl(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}
	
	public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository,
			TeacherRepository teacherRepository) {
		super();
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}



	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(long id) {
		return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id));
	}

	@Override
	public Course updateCourse(Course course, long id) {
		// Check if course is in DB
		Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id));
		
		// Set existingCourse params to new course params
		existingCourse.setCourseName(course.getCourseName());
		existingCourse.setCourseNumber(course.getCourseNumber());
		
		// Save existing course to DB
		courseRepository.save(existingCourse);
		return existingCourse;
	}

	@Override
	public void deleteCourse(long id) {
		// Check if course is in DB
		courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id)); 
		
		// Delete existing course from DB
		courseRepository.deleteById(id);

	}

	@Override
	public Course enrollStudent(long courseId, long studentId) {
		// Check if course and student are in DB
		Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
		Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
		
		// Add student to the list of enrolled students
		existingCourse.enrollStudent(existingStudent);
		
		// Save existing course to DB
		courseRepository.save(existingCourse);
		return existingCourse;
	}

	@Override
	public Course assignTeacherToCourse(long courseId, long teacherId) {
		// Check if course and teacher are in DB
		Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
		Teacher existingTeacher = teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFoundException("Teacher", "Id", teacherId));
		
		// Assign teacher to the current course
		existingCourse.assignTeacher(existingTeacher);
		
		// Save existing course to DB
		courseRepository.save(existingCourse);
		return existingCourse;
	}

	@Override
	public Course removeTeacher(long courseId) {
		// Check if course is in DB
		Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
		existingCourse.removeTeacher();
		
		// Save existing course to DB
		courseRepository.save(existingCourse);
		return existingCourse;
	}

	@Override
	public Course unenrollStudent(long courseId, long studentId) {
		// Check if course and student are in DB
		Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
		Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
		
		// Remove student to the list of enrolled students
		existingCourse.unenrollStudent(existingStudent);
		
		// Save existing course to DB
		courseRepository.save(existingCourse);
		return existingCourse;
	}

	@Override
	public Course unenrollAllStudents(long courseId) {
		// Check if course is in DB
		Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
		
		// Unenroll All Students
		existingCourse.unenrollAllStudents();
		
		// Save existing course to DB
		courseRepository.save(existingCourse);
		return existingCourse;
	}

}
