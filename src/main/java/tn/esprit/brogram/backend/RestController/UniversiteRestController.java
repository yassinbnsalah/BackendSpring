package tn.esprit.brogram.backend.RestController;

import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Universite;
import tn.esprit.brogram.backend.Services.IUniversiteService;

import java.util.List;

public class UniversiteRestController {
    IUniversiteService iUniversiteService;
    @GetMapping("findAll")
    List<Universite> findAll(){
        return iUniversiteService.findAll();
    }

    @PostMapping("addUniversite")
    Universite addUniversite(@RequestBody Universite u){
        return iUniversiteService.addUniversite(u);
    }

    @PostMapping("addAllUniversites")
    List<Universite> addAllUniversites(@RequestBody List<Universite> ls){
        return iUniversiteService.addAllUniversite(ls);
    }
    @PutMapping("editUniversite")
    Universite editUniversite(@RequestBody Universite u){
        return iUniversiteService.editUniversite(u);
    }
    @GetMapping("findById/[id}")
    Universite findById(@PathVariable("id") long id){
        return iUniversiteService.findById(id);
    }
    @DeleteMapping("deleteById/{id}")
    void deleteById(@PathVariable("id") long id){
        iUniversiteService.deleteById(id);
    }
    @DeleteMapping("delete")
    void delete(@RequestBody Universite u){
        iUniversiteService.delete(u);
    }

}
