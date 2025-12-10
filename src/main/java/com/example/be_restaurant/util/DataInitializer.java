package com.example.be_restaurant.util;

import com.example.be_restaurant.entity.User;
import com.example.be_restaurant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findByUsernameAndStatus("systemAdmin", true)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername("systemAdmin");
                    newUser.setPassword(passwordEncoder.encode("systemAdmin"));
                    newUser.setRole(User.Role.ADMIN);
                    newUser.setFullName("System Admin");
                    newUser.setCreatedAt(LocalDateTime.now());
                    newUser.setUpdatedAt(LocalDateTime.now());
                    newUser.setStatus(true);
                    return userRepository.save(newUser);
                });
        log.info("New User: {}", user.getUsername());
    }
}
