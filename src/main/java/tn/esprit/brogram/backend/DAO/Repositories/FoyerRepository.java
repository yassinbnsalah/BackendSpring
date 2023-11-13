package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.brogram.backend.DAO.Entities.Foyer;

public interface FoyerRepository extends JpaRepository<Foyer,Long> {
    @Modifying
    @Query("UPDATE Foyer f SET f.Etat = ?2 WHERE f.idFoyer = ?1")
    void updateEtatById(long idFoyer, boolean newEtat);
}
