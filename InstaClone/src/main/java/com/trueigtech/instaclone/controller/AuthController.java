// package com.trueigtech.instaclone.controller;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.trueigtech.instaclone.model.User;
// import com.trueigtech.instaclone.repository.UserRepository;
// import com.trueigtech.instaclone.security.JwtUtil;

// @CrossOrigin(origins = "http://localhost:3000")
// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//     @Autowired
//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @PostMapping("/signup")
//     public String signup(@RequestBody User user) {

//         Optional<User> existing = userRepository.findByEmail(user.getEmail());
//         if (existing.isPresent()) {
//             return "Email already exists";
//         }

//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         userRepository.save(user);

//         return "Signup successful";
//     }

//     @PostMapping("/login")
//     public String login(@RequestBody User user) {
//         String email = user.getEmail();
//         User dbUser = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         boolean valid = passwordEncoder.matches(
//                 user.getPassword(), dbUser.getPassword());

//         if (!valid) {
//             return "Invalid credentials";
//         }

//         return JwtUtil.generateToken(dbUser.getEmail());
//     }
// }


package com.trueigtech.instaclone.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trueigtech.instaclone.model.LoginRequest;
import com.trueigtech.instaclone.model.SignupRequest;
import com.trueigtech.instaclone.model.User;
import com.trueigtech.instaclone.repository.UserRepository;
import com.trueigtech.instaclone.security.JwtUtil;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {

        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("Signup successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User dbUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = JwtUtil.generateToken(dbUser.getEmail());

        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "email", dbUser.getEmail(),
                        "username", dbUser.getUsername()
                )
        );
    }
}
