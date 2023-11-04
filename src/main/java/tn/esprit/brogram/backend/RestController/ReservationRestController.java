package tn.esprit.brogram.backend.RestController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.Services.IReservationService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("addReservation")
    Reservation addReservation(@RequestBody Reservation r){
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

    @DeleteMapping("DeleteReservation/{id}")
    void DeleteReservationByID(@PathVariable("id") String id){
        iReservationService.deleteById(id);
    }

    @DeleteMapping("DeleteReservation")
    void DeleteReservation(@RequestBody Reservation r){
        iReservationService.deleteReservation(r);
    }
}
