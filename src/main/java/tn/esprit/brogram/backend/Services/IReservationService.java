package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation r);
    List<Reservation> addAllReservation(List<Reservation> ls);
    Reservation editReservation(Reservation r);

    List<Reservation> findAllReservations();

    Reservation findByIdReservation(String id);

    void deleteById(String id);
    void deleteReservation(Reservation r);
}
