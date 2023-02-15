package com.springboot.coursereg.course;

import java.util.ArrayList;
import java.util.List;

import com.springboot.coursereg.student.Student;
import com.springboot.coursereg.teacher.Teacher;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(name = "course_name")
	private String courseName; 
	
	@Column(name = "course_num")
	private String courseNumber; 
	
	public Course() { 
		
	}
	
	public Course(String courseName, String courseNumber) {
		super();
		this.courseName = courseName;
		this.courseNumber = courseNumber;
	}
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
	@JoinTable(
			name="student_enrolled", 
			joinColumns=@JoinColumn(name="course_id"), 
			inverseJoinColumns = @JoinColumn(name="student_id"))
	private List<Student> enrolledStudents = new ArrayList<>(); 
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="teacher_id", referencedColumnName="id")
	private Teacher teacher; 
	
	public void enrollStudent(Student student) { 
		enrolledStudents.add(student);  
		student.getCourses().add(this); 
	}
	
	public void unenrollStudent(Student student) { 
		enrolledStudents.removeIf(s -> s.getId() == student.getId());
		student.getCourses().remove(this); 
	}
	
	public void unenrollAllStudents() { 
		enrolledStudents.clear(); 
	}
	
	public void assignTeacher(Teacher teacher) {
		this.teacher = teacher; 
		
	}
	
	public void removeTeacher() {
		this.teacher = null; 
		
	}
	
	public static void main(String[] args) {
		
		Course myCourse = new Course("Comp Sci 1", "CS100");
		
		Student stud2 = new Student("Mike", "Tyson", "iron_mike@email.com"); 
		stud2.setId(1002);
		Student stud3 = new Student("Chris", "Pratt", "cp@email.com"); 
		stud3.setId(1003);
		Student stud4 = new Student("Bruce", "Wayne", "not_batman@email.com"); 
		stud4.setId(1004);
		
		myCourse.enrollStudent(stud2); 
		myCourse.enrollStudent(stud3);
		myCourse.enrollStudent(stud4);
		
		myCourse.getEnrolledStudents().forEach(value -> System.out.println(value));
		System.out.println(); 
		System.out.println(); 
		
		myCourse.getEnrolledStudents().removeIf(obj -> obj.getId() == 1003);
		
		myCourse.getEnrolledStudents().forEach(value -> System.out.println(value));
		System.out.println(); 
		System.out.println(); 
		
		myCourse.getEnrolledStudents().clear(); 
		System.out.println(myCourse.getEnrolledStudents().size()); 
	}
 
}
