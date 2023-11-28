package tn.esprit.brogram.backend.Services;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.extensions.Extension;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.DAO.Repositories.UserRepository;
import tn.esprit.brogram.backend.Model.LoginResponce;
import tn.esprit.brogram.backend.Services.email.EmailService;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService implements IUserService {
    private final UserRepository userRepo;
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @JsonIgnore
    @Override
    public User updateUser(User user) {
        if (userRepo.existsById(user.getId())) {
            User existing = userRepo.getReferenceById(user.getId());
            //these should be the same
            user.setPassword(existing.getPassword());
            user.setEmail(existing.getEmail());
            user.setRole(existing.getRole());
            user.setUpdatedAt(new Date());
            user.setReservations(existing.getReservations());

            System.out.println(user.toString());
            return userRepo.save(user);
        } else {
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

    @Override
    public boolean changePassword(String email, String newPassword, String oldPssword) {
        if(userRepo.existsByEmail(email)){
            User existing = userRepo.findByEmail(email);
            if(existing.getPassword()==newPassword){
                existing.setPassword(newPassword);
            }
            return true;
        }else
            return false;

    }

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
                .filter(user -> user.getRole() == Roles.ETUDIANT)
                .collect(Collectors.toList());
        etudiantUsers.sort(Comparator.comparing(User::getEmail));
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
