package me.elmaalem.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfiguration {
    
    @Bean
    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:~/test";
        return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }
}