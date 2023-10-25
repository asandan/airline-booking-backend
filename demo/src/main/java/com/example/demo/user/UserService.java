package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User signUp(User user) throws Exception {
        String password = bCrypt.encode(user.getPassword());
        try {
            user.setPassword(password);
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return user;
    }

    public User signIn(User user) throws Exception{
        User userFound = this.userRepository.findUserByEmail(user.getEmail())
                .orElseThrow(() -> new Exception("User not found"));

        if(!bCrypt.matches(user.getPassword(), userFound.getPassword())){
            throw new Exception("Invalid credentials");
        }
        return userFound;
    }


}
