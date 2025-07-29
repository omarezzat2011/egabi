package com.omarezzat.university;

import com.omarezzat.university.model.User;
import com.omarezzat.university.security.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UniversityApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    @Bean
    @Transactional
    public ApplicationRunner initializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Delete all users to reset state
            userRepository.deleteAll();

            // Insert admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("admin");
            userRepository.save(admin);

            System.out.println(userRepository.findByUsername("admin").get().getUsername());

            // Insert student user
            User student = new User();
            student.setUsername("student");
            student.setPassword(passwordEncoder.encode("student123"));
            student.setRole("student");
            userRepository.save(student);
        };
    }
}
