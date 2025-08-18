package com.example.crudthymeleaf.controllers;

import com.example.crudthymeleaf.models.Student;
import com.example.crudthymeleaf.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    public String home(){
        return "index";
    }

}
