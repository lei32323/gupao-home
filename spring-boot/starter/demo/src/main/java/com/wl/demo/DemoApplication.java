package com.wl.demo;

import com.wl.tools.format.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private JsonFormat jsonFormat;

    @GetMapping("/")
    public String hello() {
        Student wl = new Student("wl", "12");
        StringBuffer sb = new StringBuffer();
        sb.append(jsonFormat.getClass() + "<br/>");
        sb.append(jsonFormat.jsonFormat(wl) + "<br/>");
        return sb.toString();
    }

}
