package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

  @Bean
  CommandLineRunner userCommandLineRunner(UserRepository repository) {
    return args ->
      repository.save(
        new User("asan.dan.2121@mail.ru", "12345678", "Asan", "Dan", 500L)
      );
  }
}
