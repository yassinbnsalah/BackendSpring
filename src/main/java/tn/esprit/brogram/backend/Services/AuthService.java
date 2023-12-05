package tn.esprit.brogram.backend.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.DAO.Repositories.UserRepository;
import tn.esprit.brogram.backend.Model.LoginResponce;
import tn.esprit.brogram.backend.Model.RegisterDto;
import tn.esprit.brogram.backend.Services.email.EmailService;
import tn.esprit.brogram.backend.security.JwtIssuer;
import tn.esprit.brogram.backend.security.UserPrincipal;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final EmailService emailService;

    private final String url = "http://localhost:8080";


    public LoginResponce attemtptLogin(String email, String password){
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal =(UserPrincipal)authentication.getPrincipal();
        var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        var token = jwtIssuer.issue( principal.getUserId(),principal.getEmail(),roles);
        return LoginResponce.builder()
                .accessToken(token)
                .build();
    }

    public void registerUser(RegisterDto registerDto) {
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Username is taken!");
        }
        User user = new User();
        user.setNomEt(registerDto.getNomEt());
        user.setPrenomEt(registerDto.getPrenomEt());
        user.setCin(registerDto.getCin());
        user.setEcole(registerDto.getEcole());
        user.setDateNaissance(registerDto.getDateNaissance());
        user.setUpdatedAt(new Date());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(Roles.ETUDIANT);
        user.setCreatedAt(new Date());

        String token = generateVerificationToken();
        user.setVerificationToken(token);

        System.out.println(user);
        userRepo.save(user);
        sendVerificationMail(registerDto.getEmail(),token);
    }

    private String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }

    public void sendVerificationMail(String to, String verificationToken) {
        String subject = "Account Verification";
        String body = "Click the link below to verify your account:\n\n"
                + url+"/verify?token=" + verificationToken;
        emailService.sendMail(to, subject, body);
    }

    public void resetPassword(String email, String password) {
        User u = userRepo.findByEmail(email);
        System.out.println(email);
        if (u != null) {
            u.setPassword(passwordEncoder.encode(password));
            System.out.println("done");
            u.setUpdatedAt(new Date());
            userRepo.save(u);
        } else {
            throw new NotFoundException("User not found with email: " + email);
        }
    }


    public boolean isOldPasswordCorrect(String email, String oldPass) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(oldPass, user.getPassword());
        } else {
            return false;
        }
    }


    public void lastLogin(String email){
        User u = userRepo.findByEmail(email);
        u.setLastLogin(LocalDate.now());
        userRepo.save(u);
    }


}
