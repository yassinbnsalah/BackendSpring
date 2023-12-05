package tn.esprit.brogram.backend.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.*;
import tn.esprit.brogram.backend.DAO.Repositories.ChamberRepository;
import tn.esprit.brogram.backend.DAO.Repositories.ReservationRepository;
import tn.esprit.brogram.backend.DAO.Repositories.UserRepository;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Service
public class ReservationService implements IReservationService {
    ReservationRepository reservationRepository ;
    ChamberRepository chamberRepository ;
    UserRepository userRepository ;
    //EmailService emailService ;
    @Override
    public Set<Reservation> addReservation(long numero , List<Long> cin) {
        Set<Reservation> returnedReservation = new HashSet<>( );
        /*********** GENERATE id ***********/
        LocalDate dateDebutAU;
        LocalDate dateFinAU;
        int year = LocalDate.now().getYear() % 100;
        if (LocalDate.now().getMonthValue() <= 7) {
            dateDebutAU = LocalDate.of(Integer.parseInt("20" + (year - 1)), 9, 15);
            dateFinAU = LocalDate.of(Integer.parseInt("20" + year), 6, 30);
        } else {
            dateDebutAU = LocalDate.of(Integer.parseInt("20" + year), 9, 15);
            dateFinAU = LocalDate.of(Integer.parseInt("20" + (year + 1)), 6, 30);
        }
        /**************************************************/
        Chamber c = chamberRepository.findChamberByNumerochamber(numero);
        for(long cinEtudiant:cin){

            System.out.println(cinEtudiant);
            User u = userRepository.findEtudiantsByCin(cinEtudiant);
            System.out.println("USER");
            Reservation r = new Reservation();
            r.setIdReservation(dateDebutAU.getYear()+"-"+dateFinAU.getYear()+"-"+c.getBloc().getNomBloc()
                    +"-"+c.getNumerochamber()+"-"+cinEtudiant);

            r.setAnneeReservation(new Date());
            r.setDateDebut(dateDebutAU);
            r.setDateFin(dateFinAU);
            r.setEstValide(true);
            c.getRes().add(r);
            chamberRepository.save(c);
            r.getEtudiants().add(u);
            reservationRepository.save(r);
            returnedReservation.add(r);


           // emailService.sendMailReservationInformation(r,u);
        }


        return returnedReservation;
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
        Reservation r = reservationRepository.findById(id).orElse(Reservation.builder().build());
        r.setStatus(status);

        return reservationRepository.save(r);
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findReservationByEmailEtudiant(String email) {
        return reservationRepository.findReservationByEtudiants_email(email);
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
