package tn.esprit.brogram.backend.RestController;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Bloc;
import tn.esprit.brogram.backend.DAO.Entities.Chamber;
import tn.esprit.brogram.backend.DAO.Entities.Foyer;
import tn.esprit.brogram.backend.DAO.Entities.Universite;
import tn.esprit.brogram.backend.DAO.Repositories.UniversiteRepository;
import tn.esprit.brogram.backend.Services.IBlocService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("BlocRestController")
@CrossOrigin(origins = "*")

public class BlocRestController {
    @Autowired
    IBlocService iBlocService ;
    UniversiteRepository universiteRepository ;
    @GetMapping("findAll")
    List<Bloc> findAll(){
        return iBlocService.findAll();
    }

    @GetMapping("findBLocByChamber/{id}")
    Bloc findBlocByChamber(@PathVariable("id") long id){
        return iBlocService.findBlocByChamber_IdChamber(id);
    }
    @GetMapping("findBLocByFoyer/{id}")
    List<Bloc> findBlocByFoyer(@PathVariable("id") long id){
        return iBlocService.findBlocByFoyer_IdFoyer(id);
    }

    @PostMapping("addBloc/{name}")
    Bloc AddBloc(@RequestBody Bloc b , @PathVariable("name") String name){
        //add
        Universite u = universiteRepository.findUnBynomUniversite(name) ;
        Foyer f = u.getFoyer() ;
        if(b.getChambers() != null){
            for (Chamber chamber : b.getChambers()) {
                chamber.setCreatedAt(new Date());
                chamber.setUpdatedAt(new Date());
            }
        }
        b.setFoyer(f);
        b.setCreatedAt(new Date());
        b.setUpdatedAt(new Date());
        return iBlocService.addBloc(b);
    }

    @PostMapping("addAllBlocs")
    List<Bloc> addAllBlocs(@RequestBody List<Bloc> b){
        return iBlocService.addAllBlocs(b);
    }

    @PutMapping("editBloc")
    Bloc editBloc(@RequestBody Bloc b){
        return iBlocService.editBloc(b);
    }

    @GetMapping("findById/{id}")
    Bloc findById(@PathVariable("id") long id){
        return iBlocService.findById(id);
    }

    @DeleteMapping("deleteByID/{id}")
    void deleteByID(@PathVariable("id") long id){
        iBlocService.deleteByID(id);
    }

    @DeleteMapping("delete")
    void delete(@RequestBody Bloc b){
        iBlocService.delete(b);
    }
}