package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findEtudiantsByCin(long studentCIN);
    List<User> findEtudiantsByNomEtAndPrenomEt(String nom, String prenom);
    List<User> findEtudiantByEcole(String schoolName);

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
