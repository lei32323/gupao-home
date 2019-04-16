package org.springframework.test;

import org.springframework.annotation.Autowired;
import org.springframework.annotation.Controller;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

}
