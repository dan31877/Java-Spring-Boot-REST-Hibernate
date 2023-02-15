package com.springboot.coursereg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.coursereg.course.Course;
import com.springboot.coursereg.course.CourseRepository;
import com.springboot.coursereg.student.Student;
import com.springboot.coursereg.student.StudentRepository;
import com.springboot.coursereg.teacher.Teacher;
import com.springboot.coursereg.teacher.TeacherRepository;

@SpringBootApplication
public class SpringbootCourseRegApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCourseRegApplication.class, args);
	}
	
	@Autowired
	private StudentRepository studentRepository; 
	
	@Autowired
	private CourseRepository courseRepository; 
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
//		Student stud1 = new Student("Chad", "Smith", "cs@email.com"); 
//		studentRepository.save(stud1); 
//		
//		Student stud2 = new Student("Mike", "Tyson", "iron_mike@email.com"); 
//		studentRepository.save(stud2); 
//		
//		Student stud3 = new Student("Chris", "Pratt", "cp@email.com"); 
//		studentRepository.save(stud3); 
//		
//		Student stud4 = new Student("Bruce", "Wayne", "not_batman@email.com"); 
//		studentRepository.save(stud4); 
//		
//		Student stud5 = new Student("Dave", "Grohl", "foo@email.com"); 
//		studentRepository.save(stud5); 
//		
//		Teacher teacher1 = new Teacher("Nick", "Cage", "nc@email.com"); 
//		teacherRepository.save(teacher1); 
//		
//		Teacher teacher2 = new Teacher("John", "Cena", "jc@email.com"); 
//		teacherRepository.save(teacher2); 
//		
//		Teacher teacher3 = new Teacher("Henry", "Cavill", "not_superman@email.com"); 
//		teacherRepository.save(teacher3); 
//		
//		Teacher teacher4 = new Teacher("Betty", "White", "bw@email.com"); 
//		teacherRepository.save(teacher4); 
//		
//		Teacher teacher5 = new Teacher("John", "Hurt", "jh@email.com"); 
//		teacherRepository.save(teacher5); 
//		
//		Course course1 = new Course("Intro to Computer Science 2", "CS102"); 
//		courseRepository.save(course1); 
//		
//		Course course2 = new Course("Web Development", "CS263"); 
//		courseRepository.save(course2); 
//		
//		Course course3 = new Course("Calculus II", "MA310"); 
//		courseRepository.save(course3); 
//		
//		Course course4 = new Course("Data Structures and Algorythms", "CS241"); 
//		courseRepository.save(course4);
	}

}
