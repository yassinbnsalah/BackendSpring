package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Universite;

import java.util.List;

public interface UniversiteRepository extends JpaRepository<Universite,Long> {
    Universite findUnBynomUniversite(String name);
    Universite findUniversiteByEmail(String email);
    List<Universite> findByStatuts(String statuts);

    Universite findUniversiteByFoyerIdFoyer(long idFoyer);
}
