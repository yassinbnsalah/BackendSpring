package tn.esprit.brogram.backend.RestController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Foyer;
import tn.esprit.brogram.backend.DAO.Entities.Universite;
import tn.esprit.brogram.backend.DAO.Repositories.UniversiteRepository;
import tn.esprit.brogram.backend.Services.IFoyerService;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("FoyerRestController")
public class FoyerRestController {
    @Autowired
    IFoyerService iFoyerService;
    UniversiteRepository universiteRepository;
    @GetMapping("findAllFoyer")
    List<Foyer> findAll(){
        return iFoyerService.findAllFoyer();
    }

    @GetMapping("findByIdFoyer/{id}")
    Foyer findbyIdFoyer(@PathVariable("id") String id){
        return iFoyerService.findByIDFoyer(id);
    }

    @PostMapping("AddFoyer/{name}")
    Foyer AddFoyer(@RequestBody Foyer f , @PathVariable("name") String name){
        Universite u = universiteRepository.findUnBynomUniversite(name);
        // MECH BEST PARCTICE *
        u.setFoyer(f);
        universiteRepository.save(u);
        //f.setIdFoyer(u.getNomUniversite()+f.getNomFoyer());
        return iFoyerService.AddFoyer(f);
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
    void DeleteFoyerByID(@PathVariable("id") String id){
        iFoyerService.DeleteByIDFoyer(id);
    }

    @DeleteMapping("DeleteFoyer")
    void DeleteFoyer(@RequestBody Foyer f){
        iFoyerService.deleteFoyer(f);
    }
}
