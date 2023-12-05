package tn.esprit.brogram.backend.RestController;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.brogram.backend.DAO.Entities.Bloc;
import tn.esprit.brogram.backend.DAO.Entities.Image;
import tn.esprit.brogram.backend.DAO.Entities.StateUniversite;
import tn.esprit.brogram.backend.DAO.Entities.Universite;
import tn.esprit.brogram.backend.DAO.Repositories.ImageRepositroy;
import tn.esprit.brogram.backend.DAO.Repositories.UniversiteRepository;
import tn.esprit.brogram.backend.Services.IUniversiteService;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("UniversiteRestController")

public class UniversiteRestController {
    @Autowired
    IUniversiteService iUniversiteServices  ;
    @PostMapping(value = "addUniversite", consumes = MediaType.APPLICATION_JSON_VALUE)
    Universite addUniversite(@RequestBody Universite u){
        u.setStatuts("En_attente");
        return iUniversiteServices.addUniversite(u);
    }
    UniversiteRepository universiteRepository ;
    ImageRepositroy imageRepositroy ;
    @GetMapping("findUniversiteByFoyer/{id}")
    List<Universite> findUniversiteByFoyer(@PathVariable("id") long idFoyer){
        return universiteRepository.findUniversiteByFoyer_IdFoyer(idFoyer);
    }

    @PostMapping("/uploadImg/{idUniversite}")
    public Universite addImg(@RequestParam("file") MultipartFile file , @PathVariable("idUniversite") long idUniversite) {

        Universite universite = universiteRepository.findById(idUniversite).get();
        System.out.println("OK");

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Image FileDB = new Image(fileName, file.getContentType(), file.getBytes());
            //FileDB.setUniversite(universite);
            Image img = imageRepositroy.save(FileDB);
            universite.setImage(img);
            universite.setImagebyte(file.getBytes());
            universiteRepository.save(universite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return universite;
    }

    @GetMapping("findUniversiteByEmailAgent/{email}")
    Universite findUniversiteByEmailAgent(@PathVariable("email") String email){
        return iUniversiteServices.findUniversiteByEmail(email);
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

    @PutMapping("updateStatus/{id}")
    public ResponseEntity<Universite> updateStatus(@PathVariable long id, @RequestParam String status) {
        try {
            Universite updatedUniversite = iUniversiteServices.updateStatus(id, status);
            return new ResponseEntity<>(updatedUniversite, HttpStatus.OK);
        } catch (Exception ex) {
            // Handle any exceptions here
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
