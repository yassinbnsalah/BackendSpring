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
    @Autowired
    IFoyerService iFoyerService;
    UniversiteRepository universiteRepository;
    FoyerRepository foyerRepository;
    @GetMapping("findAllFoyer")
    List<Foyer> findAll(){
        return iFoyerService.findAllFoyer();
    }

    @GetMapping("findByIdFoyer/{id}")
    Foyer findbyIdFoyer(@PathVariable("id") long id){
        return iFoyerService.findByIDFoyer(id);
    }

    @PostMapping("AddFoyer/{name}")
    Foyer AddFoyer(@RequestBody Foyer f , @PathVariable("name") String name){
        Universite u = universiteRepository.findUnBynomUniversite(name);
        // MECH BEST PARCTICE *
        u.setFoyer(f);
        universiteRepository.save(u);
        f.setCreatedAt(new Date());
        //f.setIdFoyer(u.getNomUniversite()+f.getNomFoyer());
        return iFoyerService.AddFoyer(f);
    }

    @PostMapping("AddAllFoyer")
    List<Foyer> AddAllFoyer(@RequestBody List<Foyer> ls){
        //lll
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

    @DeleteMapping("DeleteFoyer")
    void DeleteFoyer(@RequestBody Foyer f){
        iFoyerService.deleteFoyer(f);
    }
}
