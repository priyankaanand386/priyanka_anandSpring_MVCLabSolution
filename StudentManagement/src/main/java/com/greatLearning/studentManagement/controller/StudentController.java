package com.greatLearning.studentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.studentManagement.model.Student;
import com.greatLearning.studentManagement.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String getAllStudents(Model theModel) {

		List<Student> theStudents = studentService.findAll();
		theModel.addAttribute("studentModel", theStudents);

		return "studentlist";
	}

	@RequestMapping("/add")
	public String addStudent(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "studentsave";
	}

	@RequestMapping("/update")
	public String updateStudent(@RequestParam("id") int id, Model theModel) {

		Student theStudent = studentService.findById(id);
		theModel.addAttribute("student", theStudent);
		return "studentsave";
	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("id") int id) {
		System.out.println(id);
		studentService.deleteById(id);
		return "redirect:/students/list";
	}

	@RequestMapping("/save")
	public String saveStudent(@RequestBody Student student) {

		Student theStudent = studentService.findById( student.getId() );
		theStudent.setFirstname(student.getFirstname());
		theStudent.setLastname(student.getLastname());
		theStudent.setCourse(student.getCourse());
		theStudent.setCountry(student.getCountry());
		this.studentService.save(theStudent);
		return "done";
	}
	
}
