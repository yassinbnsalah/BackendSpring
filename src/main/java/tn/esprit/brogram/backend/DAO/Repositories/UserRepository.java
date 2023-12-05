package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findEtudiantsByCin(long studentCIN);
    List<User> findEtudiantsByNomEtAndPrenomEt(String nom, String prenom);
    List<User> findEtudiantByEcoleAndRole(String schoolName, Roles role);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    User findByVerificationToken(String verificationToken);
    List<User> findByLastLoginBefore(LocalDate date);
}
