package tn.esprit.brogram.backend.RestController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Etudiant;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.StateReservation;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.Services.IReservationService;

import java.util.Date;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("ReservationRestController")
public class ReservationRestController {
    @Autowired
    IReservationService iReservationService;

    @GetMapping("findAllReservation")
    List<Reservation> findAll(){
        return iReservationService.findAllReservations();
    }

    @GetMapping("findReservationByID/{id}")
    Reservation findbyIDReservation(@PathVariable("id") String id){
        return iReservationService.findByIdReservation(id);
    }

    @GetMapping("findReservationByIDEtudiant/{email}")
    List<Reservation> findReservationByIDEtudiant(@PathVariable("email") String email){
        return iReservationService.findReservationByEmailEtudiant(email);
    }

    @PostMapping("addReservation")
    Reservation addReservation(@RequestBody Reservation r){

        Set<User> e = r.getEtudiants() ;

        r.setAnneeReservation(new Date());
        User[] names = e.toArray(new User[e.size()]);
        r.setIdReservation(names[0].getNomEt()+r.getAnneeReservation().getTime());
        //return r;
        return iReservationService.addReservation(r);
    }

    @PostMapping("addAllReservation")
    List<Reservation> addAllReservation(@RequestBody List<Reservation> ls){
        return  iReservationService.addAllReservation(ls);
    }

    @PutMapping("updateReservation")
    Reservation updateReservation(@RequestBody Reservation r){
        return iReservationService.editReservation(r);
    }

    @PutMapping("updateReservationStatus/{id}/{status}")
    Reservation updateReservationStatus(@PathVariable("id") String idReservation , @PathVariable("status")StateReservation status){
        return iReservationService.updateReservationState(idReservation , status);
    }
    @DeleteMapping("DeleteReservation/{id}")
    void DeleteReservationByID(@PathVariable("id") String id){
        iReservationService.deleteById(id);
    }

    @DeleteMapping("DeleteReservation")
    void DeleteReservation(@RequestBody Reservation r){
        iReservationService.deleteReservation(r);
    }
}
