package tn.esprit.brogram.backend.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.Chamber;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Repositories.ChamberRepository;

import java.util.List;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ChamberService implements IChamberService{
    ChamberRepository chamberRepository;
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
}
