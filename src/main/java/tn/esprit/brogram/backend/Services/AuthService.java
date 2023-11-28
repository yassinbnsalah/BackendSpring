package tn.esprit.brogram.backend.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.DAO.Repositories.UserRepository;
import tn.esprit.brogram.backend.Model.LoginResponce;
import tn.esprit.brogram.backend.Model.RegisterDto;
import tn.esprit.brogram.backend.Services.email.EmailService;
import tn.esprit.brogram.backend.security.JwtIssuer;
import tn.esprit.brogram.backend.security.UserPrincipal;

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
        var token = jwtIssuer.issue(principal.getUserId(),principal.getEmail(),roles);
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


//    public String sendMail(String to, String subject, String body) {
//        subject = "Account Verification";
//        body = "Click the link below to verify your account:\n\n"
//                + url+"/verify?token=" + verificationToken;
//        return emailService.sendMail(to, subject, body);
//    }


    public LoginResponce changePassword(String email, String oldPassword, String newPassword) {
        if(userRepo.existsByEmail(email)){
        User user = userRepo.findByEmail(email);

        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(user);

            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, newPassword)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            var principal = (UserPrincipal) authentication.getPrincipal();
            var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(),roles);
            return attemtptLogin(email,newPassword);
        } else {
            // Old password is incorrect
            throw new BadCredentialsException("Incorrect old password");
        }
        }
        return attemtptLogin(email,oldPassword);
    }



}
