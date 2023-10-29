package tn.esprit.brogram.backend.RestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Chamber;
import tn.esprit.brogram.backend.Services.IChamberService;


import java.util.List;

@RestController
@AllArgsConstructor
public class ChamberRestController {
    IChamberService iChamberService ;

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
        return iChamberService.addChamber(c);
    }

    @PostMapping("addAllChambers")
    List<Chamber> AddAllChambers(@RequestBody List<Chamber> ls){
        return iChamberService.addAllChambers(ls);
    }
    @PutMapping("updateChamber")
    Chamber editChamber(@RequestBody Chamber c){
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
