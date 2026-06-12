package com.sanjana.researchtracker.research_tracker.Controller;

import com.sanjana.researchtracker.research_tracker.Dao.secure.UserRepository;
import com.sanjana.researchtracker.research_tracker.Entities.secure.User;
import com.sanjana.researchtracker.research_tracker.config.JwtService;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return "User registered!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        var dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(dbUser.getUsername());
    }
}
