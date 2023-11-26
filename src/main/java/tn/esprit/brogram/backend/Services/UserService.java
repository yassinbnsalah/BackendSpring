package tn.esprit.brogram.backend.Services;



import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.Etudiant;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.DAO.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Service
//@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
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
}
