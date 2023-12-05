package tn.esprit.brogram.backend.RestController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.brogram.backend.DAO.Entities.Bloc;
import tn.esprit.brogram.backend.DAO.Entities.Foyer;
import tn.esprit.brogram.backend.DAO.Entities.Image;
import tn.esprit.brogram.backend.DAO.Entities.Universite;
import tn.esprit.brogram.backend.DAO.Repositories.BlocRepository;
import tn.esprit.brogram.backend.DAO.Repositories.FoyerRepository;
import tn.esprit.brogram.backend.DAO.Repositories.ImageRepositroy;
import tn.esprit.brogram.backend.DAO.Repositories.UniversiteRepository;
import tn.esprit.brogram.backend.Services.IFoyerService;


import java.io.IOException;
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
    BlocRepository blocRepository;
    private UniversiteRepository universiteRepository;
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
        f.setUpdatedAt(new Date());
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
    @GetMapping("findFoyerByUniversite/{id}")
    List<Foyer> findFoyerByUniversite(@PathVariable("id") long idUniversite){
        return foyerRepository.findFoyerByUniversite_IdUniversite(idUniversite);
    }
    @PutMapping("updateEtatById/{idFoyer}")
    public ResponseEntity<String> updateFoyerEtatById(@PathVariable long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        if (foyer != null) {
            foyer.setEtat(true);
            Universite universite = foyer.getUniversite();
            if (universite != null) {
                universite.setFoyer(null);
            }
            List<Bloc> blocs = foyer.getBlocs();
            if (blocs != null) {
                for (Bloc bloc : blocs) {
                    bloc.setFoyer(null);
                }
            }
            foyer.setUniversite(null);
            foyer.setBlocs(null);
            foyerRepository.save(foyer);
            return ResponseEntity.ok("Foyer Etat updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    ImageRepositroy imageRepositroy;
    @PostMapping("/uploadImg/{idFoyer}")
    public Foyer addImg(@RequestParam("file") MultipartFile file , @PathVariable("idFoyer") long idFoyer) {

        Foyer foyer = foyerRepository.findById(idFoyer).get();
        System.out.println("OK");

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Image FileDB = new Image(fileName, file.getContentType(), file.getBytes());
            //FileDB.setUniversite(universite);
            //Image img = imageRepositroy.save(FileDB);
            //foyer.setImage(img);
            foyer.setImagebyte(file.getBytes());
            foyerRepository.save(foyer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return foyer;
    }
}
