package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Chamber;

public interface ChamberRepository extends JpaRepository<Chamber,Long> {
}
