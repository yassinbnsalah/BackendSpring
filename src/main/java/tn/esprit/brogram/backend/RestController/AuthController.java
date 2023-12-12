package tn.esprit.brogram.backend.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.brogram.backend.DAO.Entities.Image;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.DAO.Repositories.ImageRepositroy;
import tn.esprit.brogram.backend.DAO.Repositories.UserRepository;
import tn.esprit.brogram.backend.DAO.errors.InvalidCredentials;
import tn.esprit.brogram.backend.DAO.errors.UserNotEnabled;
import tn.esprit.brogram.backend.DAO.errors.UserNotFoundException;
import tn.esprit.brogram.backend.Model.ChangePasswordRequest;
import tn.esprit.brogram.backend.Model.LoginRequest;
import tn.esprit.brogram.backend.Model.LoginResponce;
import tn.esprit.brogram.backend.Model.RegisterDto;
import tn.esprit.brogram.backend.Services.AuthService;
import tn.esprit.brogram.backend.Services.UserService;
import tn.esprit.brogram.backend.Services.email.EmailService;
import tn.esprit.brogram.backend.security.JwtIssuer;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final EmailService emailService;
    private final ImageRepositroy imageRepositroy;

    @CrossOrigin(origins = "http://localhost:4200/auth/login", maxAge = 3600, allowCredentials = "true")
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponce> login(@RequestBody @Validated LoginRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
                request.getPassword() == null || request.getPassword().isEmpty()) {
            System.out.println("Email and password are required");
            return ResponseEntity.badRequest().build();
        }
        User user = userRepo.findByEmail(request.getEmail());
        if (user == null) {
            throw new UserNotFoundException("User not found by email");
        }
        if (!user.getEnabled()) {
            throw new UserNotEnabled("User is not authorized");
        }
        try {
            LoginResponce loginResponse = authService.attemtptLogin(request.getEmail(), request.getPassword());
            authService.lastLogin(request.getEmail());
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            // Catch the specific exception and rethrow it as InvalidCredentials
            throw new InvalidCredentials("Invalid credentials: " + e.getMessage());
        }
    }



        @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
        User user = userRepo.findByEmail(registerDto.getEmail());
        if (user != null) {
            throw new UserNotFoundException("User exist by that email");
        }
        authService.registerUser(registerDto);
        return ResponseEntity.ok("User registered successfully");
    }



    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("token") String verificationToken) {
        if (validateVerificationToken(verificationToken)) {
            return ResponseEntity.ok("User verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid verification token");
        }
    }

    private boolean validateVerificationToken(String verificationToken) {
        User u = userRepo.findByVerificationToken(verificationToken);
        if (u.getVerificationToken().equals(verificationToken)) {
            if (u.getEnabled()) {
                return true;
            }else if(!u.getEnabled()){
                u.setEnabled(true);
                userRepo.save(u);
                return true;
            }
        }
        return false;
    }
}
