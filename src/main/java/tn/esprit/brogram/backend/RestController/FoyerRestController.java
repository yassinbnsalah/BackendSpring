package tn.esprit.brogram.backend.RestController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Foyer;
import tn.esprit.brogram.backend.DAO.Entities.Universite;
import tn.esprit.brogram.backend.DAO.Repositories.FoyerRepository;
import tn.esprit.brogram.backend.DAO.Repositories.UniversiteRepository;
import tn.esprit.brogram.backend.Services.IFoyerService;


import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("FoyerRestController")
public class FoyerRestController {
    private final FoyerRepository foyerRepository;
    @Autowired
    public FoyerRestController(FoyerRepository foyerRepository) {
        this.foyerRepository = foyerRepository;
    }
    @Autowired
    IFoyerService iFoyerService;
    UniversiteRepository universiteRepository;
    @GetMapping("findAllFoyer")
    List<Foyer> findAll(){
        return iFoyerService.findAllFoyer();
    }

    @GetMapping("findFoyerByUnversiteName/{nom}")
    List<Foyer> findFoyerByUniversiteNomUniversite(@PathVariable("nom") String nom){
        return iFoyerService.findFoyerByUniversersite(nom);
    }
    @GetMapping("findByIdFoyer/{id}")
    Foyer findbyIdFoyer(@PathVariable("id") long id){
        return iFoyerService.findByIDFoyer(id);
    }


    @PostMapping("AddFoyer/{name}")
    Foyer AddFoyer(@RequestBody Foyer f , @PathVariable("name") String name){

        return iFoyerService.AddFoyer(f,name);
    }

    @PostMapping("AddAllFoyer")
    List<Foyer> AddAllFoyer(@RequestBody List<Foyer> ls){
        return iFoyerService.AddAllFoyer(ls);
    }

    @PutMapping("UpdateFoyer")
    Foyer updateFoyer(@RequestBody Foyer f){
        return iFoyerService.editFoyer(f);
    }

    @DeleteMapping("DeleteFoyerByID/{id}")
    void DeleteFoyerByID(@PathVariable("id") long id){
        iFoyerService.DeleteByIDFoyer(id);
    }

    @DeleteMapping("DeleteFoyer")
    void DeleteFoyer(@RequestBody Foyer f){
        iFoyerService.deleteFoyer(f);
    }
    @PutMapping("updateEtatById/{idFoyer}")
    public ResponseEntity<String> updateFoyerEtatById(@PathVariable long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        if (foyer != null) {
            foyer.setEtat(true);
            foyerRepository.save(foyer);
            return ResponseEntity.ok("Foyer Etat updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
