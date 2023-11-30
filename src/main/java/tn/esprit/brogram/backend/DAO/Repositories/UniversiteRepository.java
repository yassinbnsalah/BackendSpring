package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.brogram.backend.DAO.Entities.Universite;

import java.util.List;
import java.util.Optional;

public interface UniversiteRepository extends JpaRepository<Universite,Long> {
    Universite findUnBynomUniversite(String name);

    Universite findUniversiteByEmail(String email);

    List<Universite> findByStatuts(String statuts);

    List<Universite> findUniversiteByAdresse(String adresse);

    Universite findUniversiteByFoyerIdFoyer(long idFoyer);

    Universite findUniversiteByNomUniversiteAndEmail(String name, String email);

}
