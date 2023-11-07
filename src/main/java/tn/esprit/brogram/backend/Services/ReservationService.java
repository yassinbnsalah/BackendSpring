package tn.esprit.brogram.backend.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.StateReservation;
import tn.esprit.brogram.backend.DAO.Repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservationService implements IReservationService {
    ReservationRepository reservationRepository ;

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public List<Reservation> addAllReservation(List<Reservation> ls) {
        return reservationRepository.saveAll(ls);
    }

    @Override
    public Reservation editReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation updateReservationState(String id, StateReservation status) {
        Optional<Reservation> r = reservationRepository.findById(id);

        return null;
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findByIdReservation(String id) {
        return reservationRepository.findById(id).orElse(Reservation.builder().build());
    }

    @Override
    public void deleteById(String id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void deleteReservation(Reservation r) {
        reservationRepository.delete(r);

    }
}
