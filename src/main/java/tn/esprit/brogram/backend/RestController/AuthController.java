package tn.esprit.brogram.backend.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.DAO.Repositories.UserRepository;
import tn.esprit.brogram.backend.Model.ChangePasswordRequest;
import tn.esprit.brogram.backend.Model.LoginRequest;
import tn.esprit.brogram.backend.Model.LoginResponce;
import tn.esprit.brogram.backend.Model.RegisterDto;
import tn.esprit.brogram.backend.Services.AuthService;
import tn.esprit.brogram.backend.Services.UserService;
import tn.esprit.brogram.backend.Services.email.EmailService;
import tn.esprit.brogram.backend.security.JwtIssuer;

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


    @CrossOrigin(origins = "http://localhost:4200/auth/login", maxAge = 3600, allowCredentials = "true")
    @PostMapping("/auth/login")
    public LoginResponce login(@RequestBody @Validated LoginRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
                request.getPassword() == null || request.getPassword().isEmpty()) {
            System.out.println("Email and password are required");
        }
        return authService.attemtptLogin(request.getEmail(), request.getPassword());
    }


    @CrossOrigin(origins = "http://localhost:4200/auth/register", maxAge = 3600, allowCredentials = "true")
    @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
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




//    public ResponseEntity register(@RequestBody RegisterDto registerDto) {
//        if (userRepo.existsByEmail(registerDto.getEmail())) {
//            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
//        }
//        User user = new User();
//        user.setNomEt(registerDto.getNomEt());
//        user.setPrenomEt(registerDto.getPrenomEt());
//        user.setCin(registerDto.getCin());
//        user.setEcole(registerDto.getEcole());
//        user.setDateNaissance(registerDto.getDateNaissance());
//
//        user.setEmail(registerDto.getEmail());
//        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
//        user.setRole(Roles.ETUDIANT);
//
//        System.out.println(registerDto);
//        System.out.println(user);
//        userRepo.save(user);
//        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
//    }

}
