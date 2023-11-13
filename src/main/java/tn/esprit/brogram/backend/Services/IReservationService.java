package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.StateReservation;

import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation r);
    List<Reservation> addAllReservation(List<Reservation> ls);
    Reservation editReservation(Reservation r);
    Reservation updateReservationState(String id , StateReservation status);
    List<Reservation> findAllReservations();
    List<Reservation> findReservationByEmailEtudiant(String email);
    Reservation findByIdReservation(String id);

    void deleteById(String id);
    void deleteReservation(Reservation r);
}
