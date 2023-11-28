package tn.esprit.brogram.backend.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.Chamber;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.TypeChamber;
import tn.esprit.brogram.backend.DAO.Repositories.ChamberRepository;
import tn.esprit.brogram.backend.DAO.Repositories.ReservationRepository;

import java.util.*;

import java.util.List;

@AllArgsConstructor
@Service
public class ChamberService implements IChamberService{
    ChamberRepository chamberRepository;
    ReservationRepository reservationRepository ;
    @Override
    public Chamber addChamber(Chamber c) {
        return chamberRepository.save(c) ;
    }

    @Override
    public Chamber addChamberReservation(long idCh , Reservation r) {
        Chamber ch = chamberRepository.findById(idCh).orElse(Chamber.builder().build());
        Set<Reservation> reservations = ch.getRes();
        ch.getRes().add(r);

        return chamberRepository.save(ch);
    }

    @Override
    public Chamber findChamberByResIdReservation(String idReservation) {
        return chamberRepository.findChamberByResIdReservation(idReservation);
    }


    @Override
    public List<Chamber> addAllChambers(List<Chamber> ls) {
        return chamberRepository.saveAll(ls);
    }

    @Override
    public Chamber editChamber(Chamber c) {
        return chamberRepository.save(c);
    }

    @Override
    public List<Chamber> findAll() {
        return chamberRepository.findAll();
    }

    @Override
    public Chamber findById(long id) {
        Chamber c = chamberRepository.findById(id).orElse(Chamber.builder().idChamber(0).numerochamber(0).build());
        if(c.getBloc() == null){
            System.out.println("this chamber m3andehch blog ");
        }
        return chamberRepository.findById(id).orElse(Chamber.builder().idChamber(0).numerochamber(0).build());
    }

    @Override
    public void deleteByID(long id) {
        chamberRepository.deleteById(id);

    }

    @Override
    public void delete(Chamber c) {
        chamberRepository.delete(c);

    }

    @Override
    public List<Chamber> findChamberByBlocFoyerUniversiteNomUniversite(String nomUniversite) {

        return chamberRepository.findChamberByBlocFoyerUniversiteNomUniversite(nomUniversite);
    }

    @Override
    public List<Chamber> findAvailableChamberByBlocFoyerUniversiteNomUniversite(String nomUniversite) {
        List<Chamber> chambers  =chamberRepository.findChamberByBlocFoyerUniversiteNomUniversite(nomUniversite);
        List<Chamber> finalChambers  = new ArrayList<>( );
        for (Chamber c :chambers){
            boolean test = false ;
            Set<Reservation> reservations = c.getRes();
            int i = (int) reservations.stream().filter(Reservation::getEstValide).count();
            if(c.getTypeC().equals(TypeChamber.Simple)&&i==0){
                test = true ;
            }else if (c.getTypeC().equals(TypeChamber.Double) && i<=1){
                test = true ;
            }else if (c.getTypeC().equals(TypeChamber.Triple) && i<=2){
                test = true ;
            }
            if(test){
                finalChambers.add(c);
            }
        }
        return finalChambers;
    }
}
