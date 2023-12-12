package tn.esprit.brogram.backend.RestController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.brogram.backend.DAO.Entities.Chamber;
import tn.esprit.brogram.backend.DAO.Entities.Image;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.TypeChamber;
import tn.esprit.brogram.backend.DAO.Repositories.ChamberRepository;
import tn.esprit.brogram.backend.DAO.Repositories.ImageRepository;
import tn.esprit.brogram.backend.Services.IChamberService;


import java.io.IOException;
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
    ImageRepository imageRepositroy ;

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
        c.setEtat(true);
        return iChamberService.addChamber(c);

    }
    @PutMapping("putChamberReservation/{id}")
    Chamber putChamberReservation(@PathVariable("id") long idCh , @RequestBody Reservation r){
        return iChamberService.addChamberReservation(idCh , r);
    }
    @GetMapping("findChamberByReservationID/{id}")
    Chamber getChamberByReservation(@PathVariable("id") String idReservation){
        System.out.println("hey");
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
    @GetMapping("getChamberList/{nomBloc}")
    List<Chamber> getChambresParNomBloc(@PathVariable("nomBloc") String nomBloc){
        return iChamberService.getChambresParNomBloc(nomBloc);
    }
    @GetMapping("nbChambreParTypeEtBloc/{type}/{idBloc}")
    long nbChambreParTypeEtBloc(@PathVariable("type") TypeChamber type , @PathVariable("idBloc") long idBloc){
        return iChamberService.nbChambreParTypeEtBloc(type , idBloc);
    }
    @GetMapping("chamberListNonReserver/{type}/{nomFoyer}")
    List<Chamber> getChambresNonReserveParNomFoyerEtTypeChambre(@PathVariable("type") TypeChamber type, @PathVariable("nomFoyer") String nomFoyer) {
        return iChamberService.getChambresNonReserveParNomFoyerEtTypeChambre(nomFoyer, type);
    }
    @PostMapping("uploadImg/{idChamber}")
    public Chamber addImg(@RequestParam("file") MultipartFile file , @PathVariable("idChamber") long idChamber) {

        Chamber chamber = chamberRepo.findById(idChamber).get();
        System.out.println("OK");

        try {
       //     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
         //   Image FileDB = new Image(fileName, file.getContentType(), file.getBytes());
            //FileDB.setChamber(chamber);
        //    Image img = imageRepositroy.save(FileDB);
          //  chamber.setImage(img);
            chamber.setImagebyte(file.getBytes());
            chamberRepo.save(chamber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return chamber;
    }

    @GetMapping("/getChambersByType/{type}")
    public List<Chamber> getChambersByType(@PathVariable TypeChamber type) {
        return iChamberService.getChambersByType(type);
    }



    @GetMapping("/byTypeAndBloc")
    public ResponseEntity<List<Chamber>> getChambersByTypeAndBloc(
            @RequestParam("type") TypeChamber type,
            @RequestParam("blocName") String blocName) {
        List<Chamber> chambers = iChamberService.getChambersByTypeAndBlocName(type, blocName);
        return ResponseEntity.ok(chambers);
    }
    @PostMapping("/affecterBlocAChambre")
    public ResponseEntity<?> affecterBlocAChambre(@RequestParam long idChamber, @RequestParam long idBloc) {
        iChamberService.affecterBlocAChambre(idChamber, idBloc);
        return ResponseEntity.ok().build();
    }
}
