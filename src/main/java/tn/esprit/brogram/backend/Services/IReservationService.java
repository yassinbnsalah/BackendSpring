package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.StateReservation;

import java.util.List;
import java.util.Set;

public interface IReservationService {
    Set<Reservation> addReservation(long numero , List<Long> cin, boolean autoRenew);
    List<Reservation> addAllReservation(List<Reservation> ls);
    Reservation editReservation(Reservation r);
    Reservation updateReservationState(String id , StateReservation status);
    List<Reservation> findAllReservations();
    List<Reservation> findReservationByEmailEtudiant(String email);
    Reservation findByIdReservation(String id);

    void deleteById(String id);
    void deleteReservation(Reservation r);
}
