package org.springframework.test;

import org.springframework.annotation.Autowired;
import org.springframework.annotation.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserController userController;
}
