package org.springframework.test;

import org.springframework.annotation.Autowired;
import org.springframework.annotation.Controller;
import org.springframework.annotation.RequestMapping;
import org.springframework.annotation.RequestParam;
import org.springframework.test.service.IUserService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping("/hello")
    public ModelAndView hello(@RequestParam("name") String name,@RequestParam("data") String data,@RequestParam("token") String token){
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("data",data);
        map.put("token",token);
        userService.hello(name);
        ModelAndView modelAndView = new ModelAndView("first",map);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(@RequestParam("name") String name){
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        userService.hello(name);
        ModelAndView modelAndView = new ModelAndView("first",map);
        return modelAndView;
    }

}
