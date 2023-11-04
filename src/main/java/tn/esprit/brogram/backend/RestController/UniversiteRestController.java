package tn.esprit.brogram.backend.RestController;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Universite;
import tn.esprit.brogram.backend.Services.IUniversiteService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("UniversiteRestController")

public class UniversiteRestController {
    @Autowired
    IUniversiteService iUniversiteServices  ;
    @PostMapping(value = "addUniversite", consumes = MediaType.APPLICATION_JSON_VALUE)
    Universite addUniversite(@RequestBody Universite u){

        System.out.println("add universite");
        return iUniversiteServices.addUniversite(u);
    }

    @GetMapping("findAll")
    List<Universite> UnifindAll(){
        return iUniversiteServices.UnifindAll();
    }


    @PostMapping("addAllUniversites")
    List<Universite> addAllUniversites(@RequestBody List<Universite> ls){
        return iUniversiteServices.addAllUniversite(ls);
    }
    @PutMapping("editUniversite")
    Universite editUniversite(@RequestBody Universite u){
        return iUniversiteServices.editUniversite(u);
    }
    @GetMapping("findById/{id}")
    Universite UnifindById(@PathVariable("id") long id){
        return iUniversiteServices.UnifindById(id);
    }
    @DeleteMapping("deleteById/{id}")
    void UnideleteById(@PathVariable("id") long id){
        iUniversiteServices.UnideleteById(id);
    }
    @DeleteMapping("delete")
    void Unidelete(@RequestBody Universite u){
        iUniversiteServices.Unidelete(u);
    }

}
