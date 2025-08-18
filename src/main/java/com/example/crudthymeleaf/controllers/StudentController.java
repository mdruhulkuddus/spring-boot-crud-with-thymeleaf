package com.example.crudthymeleaf.controllers;

import com.example.crudthymeleaf.models.Student;
import com.example.crudthymeleaf.models.StudentDto;
import com.example.crudthymeleaf.repositories.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {


    @Autowired
    private StudentRepository studentRepository;

    @GetMapping({"", "/"})
    public String getStudents(Model model) {
        var students = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
//        students.forEach(System.out::println);
        model.addAttribute("students", students);
        return "students/index";
    }

    @GetMapping("/create")
    public String createStudent(Model model) {
        StudentDto studentDto = new StudentDto();
        model.addAttribute("studentDto", studentDto);
        return "students/create";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("studentDto") StudentDto studentDto,  BindingResult result,  Model model) {
        if(result.hasErrors()){
            return "students/create";
        }

        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setAddress(studentDto.getAddress());
        student.setStatus(studentDto.getStatus());

        try {
            studentRepository.save(student);
        }catch (Exception e){
            result.addError(
                    new FieldError("studentDto", "email", studentDto.getEmail(), false, null, null,
                            "Email address already exists")
            );
            return "students/create";
        }

        return "redirect:/students";
    }

    @GetMapping("/edit")
    public String editStudent(Model model, @RequestParam int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if(student == null){
            return "redirect:/students";
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        studentDto.setAddress(student.getAddress());
        studentDto.setStatus(student.getStatus());

        model.addAttribute("student", student);
        model.addAttribute("studentDto", studentDto);

        return "students/edit";
    }

    @PostMapping("/update")
    public String updateStudent(@Valid @ModelAttribute("studentDto") StudentDto studentDto,  BindingResult result,  Model model){

        Student student = studentRepository.findById(studentDto.getId()).orElse(null);
        if(student == null){
            return "redirect:/students";
        }

        model.addAttribute("student", student);
        if(result.hasErrors()){
            return "students/edit";
        }

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setAddress(studentDto.getAddress());
        student.setStatus(studentDto.getStatus());

        try {
            studentRepository.save(student);
        }catch (Exception e){
            result.addError(
                    new FieldError("studentDto", "email", studentDto.getEmail(), false, null, null,
                            "Email address already exists")
            );
            return "students/edit";
        }

        return "redirect:/students";
    }

    @GetMapping("delete")
    public String deleteStudent(Model model, @RequestParam int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if(student != null){
            studentRepository.delete(student);
        }
        return "redirect:/students";
    }
}
