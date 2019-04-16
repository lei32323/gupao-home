package com.dy.demo;

import com.dy.spring.annotation.Autowired;
import com.dy.spring.annotation.Controller;
import com.dy.spring.annotation.RequestMapping;
import com.dy.spring.annotation.RequestParam;
import com.dy.spring.webmvc.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentSercvice;


    @RequestMapping("/hello")
    public ModelAndView hello(@RequestParam("name") String name){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        ModelAndView modelAndView = new ModelAndView("first",map);

        return modelAndView;
    }

}
