package com.dy.demo;

import com.dy.spring.annotation.Autowired;
import com.dy.spring.annotation.Controller;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentSercvice;
}
