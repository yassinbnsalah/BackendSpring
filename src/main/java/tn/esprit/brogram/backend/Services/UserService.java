package tn.esprit.brogram.backend.Services;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.DAO.Repositories.UserRepository;
import tn.esprit.brogram.backend.Model.LoginResponce;
import tn.esprit.brogram.backend.Services.email.EmailService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService implements IUserService {
    private final UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    public UserService(UserRepository userRepo) {
       this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
       }


    public User findByEmail(String email) {
        if(this.userRepo.findByEmail(email) != null){

            return this.userRepo.findByEmail(email);
        }else{
            User user = new User();
            user.setId(0L);
            return user ;
        }
    }

    @JsonIgnore
    @Override
    public User updateUser(User user) {
        System.out.println("test");
        if (userRepo.existsById(user.getId())) {
            System.out.println("test");
            User existing = userRepo.getReferenceById(user.getId());
            user.setPassword(existing.getPassword());
            user.setEmail(existing.getEmail());
            user.setRole(existing.getRole());
            user.setUpdatedAt(new Date());
            user.setReservations(existing.getReservations());

            System.out.println(user.toString());
            System.out.println("USER is = "+user);
            return userRepo.save(user);
        } else {
            System.out.println("exeption");
            throw new RuntimeException("User not found with id: " + user.getId());
        }
    }

    @Override
    public List<User> findEtudiantByEcoleAndRole(String schoolName, Roles role) {
        List<User> etudiants = this.userRepo.findEtudiantByEcoleAndRole(schoolName,role);
        List<User> finalEtudiants = new ArrayList<>();
        for(User etudiant : etudiants){
            boolean test = false ;
            Set<Reservation> reservations = etudiant.getReservations();

            for (Reservation reservation : reservations){
                if(reservation.getEstValide() && reservation.getAnneeReservation().getYear()==new Date().getYear()){
                    test = true ;
                }
            }
            if(!test){
                finalEtudiants.add(etudiant);
            }
        }
        return finalEtudiants;
    }

//    @Override
//    public boolean changePassword(String email, String newPassword, String oldPssword) {
//        if(userRepo.existsByEmail(email)){
//            User existing = userRepo.findByEmail(email);
//            if(existing.getPassword()==newPassword){
//                existing.setPassword(newPassword);
//            }
//            return true;
//        }else
//            return false;
//
//    }

    @Override
    public void saveVerificationToken(long id, String verfi) {
        User u = userRepo.getReferenceById(id);
        u.setVerificationToken(verfi);
        userRepo.save(u);
    }

    @Override
    public User findByVerificationToken(String verificationToken) {
        return userRepo.findByVerificationToken(verificationToken);
    }


    @Override
    public List<User> getEtudiantUsers() {
        List<User> allUsers = userRepo.findAll();

        List<User> etudiantUsers = allUsers.stream()
                .filter(user -> user.getRole() == Roles.ETUDIANT || user.getRole() == Roles.AGENTUNIVERSITE)
                .sorted(Comparator.comparing(User::getEmail))
                .collect(Collectors.toList());

        return etudiantUsers;
    }



    @Override
    public User enableOrDisable(String email) {
        User u = userRepo.findByEmail(email);
        if (u != null) {
            boolean isEnabled = u.getEnabled();
            u.setEnabled(!isEnabled);
            userRepo.save(u);
            return u;
        } else {
            throw new NotFoundException("User not found with email: " + email);
        }
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword) {
        if(userRepo.existsByEmail(email)) {
            User user = userRepo.findByEmail(email);
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepo.save(user);
            } else {
                throw new BadCredentialsException("Incorrect old password");
            }
        }
    }
    
    @Scheduled(cron = "0 0 0 * * ?") // Run every day at midnight
    @Override
    public void disableInactiveAccounts() {
        List<User> inactiveUsers = userRepo.findByLastLoginBefore(LocalDate.now().minusDays(90));
        for (User user : inactiveUsers) {
            user.setEnabled(false);
            userRepo.save(user);
        }
    }






//    public boolean verify(String verificationCode) {
//        User user = userRepo.findByVerificationCode(verificationCode);
//
//        if (user == null || user.isEnabled()) {
//            return false;
//        } else {
//            user.setVerificationCode(null);
//            user.setEnabled(true);
//            repo.save(user);
//
//            return true;
//        }
//    }


}
