package com.example.demo.JPATransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig{
    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}