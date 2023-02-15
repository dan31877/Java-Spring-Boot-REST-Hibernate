package com.springboot.coursereg.student;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.coursereg.course.Course;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(name = "first_name")
	private String firstName; 
	
	@Column(name = "last_name")
	private String lastName; 
	
	@Column(name = "email")
	private String email;
	
	public Student() {
		
	}
	
	public Student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}



	@JsonIgnore
	@ManyToMany(mappedBy = "enrolledStudents", fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
	private List<Course> courses = new ArrayList<>();
	
	public void enrollStudentIntoCourse(Course course) { 
		this.courses.add(course);
		course.getEnrolledStudents().add(this); 
	}
	
	public void unenrollStudentFromCourse(Course course) { 
		this.courses.remove(course);
		course.getEnrolledStudents().remove(this); 
	}
	
	public void unenrollStudentFromAllCourses() {
		List<Course> existingCourse = new ArrayList<>(this.getCourses()); 
		
		for(Course course : existingCourse) {
			course.getEnrolledStudents().remove(this); 
			this.courses.remove(course);
		}
	}

}
