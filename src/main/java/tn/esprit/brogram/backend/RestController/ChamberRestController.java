package tn.esprit.brogram.backend.RestController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Chamber;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Repositories.ChamberRepository;
import tn.esprit.brogram.backend.Services.IChamberService;


import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("ChamberRestController")
public class ChamberRestController {
    @Autowired
    IChamberService iChamberService ;
    @Autowired
    ChamberRepository chamberRepo;

    @GetMapping("findChambersbyUniversite/{nom}")
    List<Chamber> findChambersbyUniversite(@PathVariable("nom") String nom){
        return iChamberService.findChamberByBlocFoyerUniversiteNomUniversite(nom);
    }
    @GetMapping("findAvailableChambersbyUniversite/{nom}")
    List<Chamber> findAvailableChambersbyUniversite(@PathVariable("nom") String nom){
        return iChamberService.findAvailableChamberByBlocFoyerUniversiteNomUniversite(nom);
    }
    @GetMapping("findAllChambers")
    List<Chamber> findAll(){
        return iChamberService.findAll();
    }

    @GetMapping("findChamberByID/{id}")
    Chamber findChamberByID(@PathVariable("id") long id){
        return iChamberService.findById(id);
    }
    @PostMapping("addChamber")
    Chamber addChamber(@RequestBody Chamber c){
        c.setCreatedAt(new Date());
        return iChamberService.addChamber(c);

    }
    @PutMapping("putChamberReservation/{id}")
    Chamber putChamberReservation(@PathVariable("id") long idCh , @RequestBody Reservation r){
        return iChamberService.addChamberReservation(idCh , r);
    }
    @GetMapping("getChambersByReservation/{id}")
    Chamber getChamberByReservation(@PathVariable("id") String idReservation){
        return iChamberService.findChamberByResIdReservation(idReservation) ;
    }

    @PostMapping("/addAllChambers")
    List<Chamber> AddAllChambers(@RequestBody List<Chamber> ls){
        return iChamberService.addAllChambers(ls);
    }
    @PutMapping("updateChamber")
    Chamber editChamber(@RequestBody Chamber c){

        c.setUpdatedAt(new Date());
        return iChamberService.editChamber(c);
    }

    @DeleteMapping("deleteChamberById/{id}")
    void DeleteChamberByID(@PathVariable("id") long id){

        iChamberService.deleteByID(id);
    }

    @DeleteMapping("deleteChamber")
    void DeleteChmber(@RequestBody Chamber c){
        iChamberService.delete(c);
    }
}
