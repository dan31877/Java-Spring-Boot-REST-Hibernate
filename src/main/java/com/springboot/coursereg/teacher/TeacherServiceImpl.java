package com.springboot.coursereg.teacher;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.coursereg.exception.ResourceNotFoundException;
import com.springboot.coursereg.student.Student;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	private TeacherRepository teacherRepository;

	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}

	@Override
	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher getTeacherById(long id) {
		return teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher", "Id", id));
	}

	@Override
	public Teacher updateTeacher(Teacher teacher, long id) {
		// Check if teacher is in DB
		Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher", "Id", id));
		
		// Set existingTeacher params to new teacher params
		existingTeacher.setFirstName(teacher.getFirstName());
		existingTeacher.setLastName(teacher.getLastName());
		existingTeacher.setEmail(teacher.getEmail());
		
		// Save existing teacher to DB
		teacherRepository.save(existingTeacher);
		return existingTeacher;
	}
	
	@Override
	public Teacher removeTeacherFromAllCourses(long id) {
		// Check if teacher is in DB
		Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher", "Id", id));
		
		// Remove teacher from all assigned courses
		existingTeacher.removeTeacherFromAllCourses();
		
		// Save existing teacher to DB
		teacherRepository.save(existingTeacher);
		return existingTeacher;
	}

	@Override
	public void deleteTeacher(long id) {
		// Check if teacher is in DB
		Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher", "Id", id));
		
		// Remove teacher from all assigned courses
		existingTeacher.removeTeacherFromAllCourses();
		
		// Delete existing teacher from DB
		teacherRepository.deleteById(id);
	}

}
