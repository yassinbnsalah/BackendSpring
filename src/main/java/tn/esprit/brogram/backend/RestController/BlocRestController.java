package tn.esprit.brogram.backend.RestController;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.*;
import tn.esprit.brogram.backend.DAO.Repositories.BlocRepository;
import tn.esprit.brogram.backend.DAO.Repositories.UniversiteRepository;
import tn.esprit.brogram.backend.Services.IBlocService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("BlocRestController")
@CrossOrigin(origins = "*")

public class BlocRestController {
    @Autowired
    IBlocService iBlocService ;
    UniversiteRepository universiteRepository ;
    BlocRepository blocRepository;
    @GetMapping("findAll")
    List<Bloc> findAll(){
        return iBlocService.findAll();
    }

    @GetMapping("findAllByuniversite/{name}")
    List<Bloc> findAllByuniversite(@PathVariable("name") String name){
        Universite u = universiteRepository.findUnBynomUniversite(name) ;
      Foyer f=u.getFoyer();
      return f.getBlocs();
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
                chamber.setBloc(b);
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
    @GetMapping("calculateAverageCapacity")
    Map<Long,Double> calculateAverageCapacity(){
        List<Bloc> allbloc=iBlocService.findAll();
        return allbloc.stream().collect(Collectors.toMap(
                Bloc::getIdBloc,
                bloc -> iBlocService.calculateAverageCapacity(bloc.getIdBloc())
        ));
    }
    @GetMapping("countChambersByType/{blocId}")
    List<Object[]> countChambersByType(@PathVariable("blocId") long blocId) {
        return iBlocService.countChambersByType(blocId);
    }
}