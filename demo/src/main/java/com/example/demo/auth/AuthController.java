package com.example.demo.auth;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/api/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("api/auth/signup")
    public User signUp(@RequestBody User user) throws Exception{
        return this.userService.signUp(user);
    }

    @PostMapping("api/auth/signin")
    public User signIn(@RequestBody User user) throws Exception{
        return this.userService.signIn(user);
    }
}
