package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
}
