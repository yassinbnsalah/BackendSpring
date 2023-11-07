package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Universite;

public interface UniversiteRepository extends JpaRepository<Universite,Long> {
    Universite findUnBynomUniversite(String name);
}
