package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Chamber;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.TypeChamber;

import java.util.List;
import java.util.Set;

public interface ChamberRepository extends JpaRepository<Chamber,Long> {
  //  Chamber findByRes(Set<Reservation> r);
    Chamber findChamberByResIdReservation(String id);
    List<Chamber> findChamberByBlocFoyerUniversiteNomUniversite(String nomUniversite);
    Chamber findChamberByNumerochamber(long numero);
    List<Chamber> findChamberByTypeCAndAndBlocFoyerUniversiteNomUniversite(TypeChamber typeChamber , String nomUniversite);
}
