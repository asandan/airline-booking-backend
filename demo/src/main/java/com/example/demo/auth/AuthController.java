package com.example.demo.auth;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<?> signUp(@RequestBody User user) throws Exception{
        try {
            User newUser = this.userService.signUp(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody User user) {
        try {
            User userFound = this.userService.signIn(user);
            return ResponseEntity.ok(userFound); // Return the user if found.
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"); // Return a 404 response with an error message.
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Return a 400 response with an error message.
        }
    }

}
