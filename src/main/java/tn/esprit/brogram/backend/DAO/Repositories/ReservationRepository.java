package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.Universite;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,String> {
    //List<Reservation> findReservationByEtudiantsidEtudiant(int id);
    List<Reservation> findReservationByEtudiants_email(String email);


}
