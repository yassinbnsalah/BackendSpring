package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Chamber;
import tn.esprit.brogram.backend.DAO.Entities.Demande;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande,Integer> {
    List<Demande> findDemandeByEcole(String ecole);

    List<Demande> findDemandeByEmail(String email);
}
