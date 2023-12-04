package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Documents;
import tn.esprit.brogram.backend.DAO.Entities.Universite;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Documents,Long> {
    List<Documents> findByUniversiteIdUniversite(long idUniversite);

}
