package tn.esprit.brogram.backend.RestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Etudiant;
import tn.esprit.brogram.backend.Services.IEtudiantService;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("EtudiantRestController")
public class EtudiantRestController {
    IEtudiantService iEtudiantService;

    @GetMapping("findAllEtudiant")
    List<Etudiant> findAll(){
        return iEtudiantService.findAll();
    }

    @GetMapping("findbyId/{id}")
    Etudiant findByID(@PathVariable("id") long id){
        return iEtudiantService.findById(id);
    }

    @PostMapping("addEtudiant")
    Etudiant addEtudiant (@RequestBody Etudiant e){
        return iEtudiantService.addEtudiant(e);
    }

    @PostMapping("addAllEtudiant")
    List<Etudiant> addAllEtudiant(@RequestBody List<Etudiant> ls){
        return  iEtudiantService.addAllEtudiant(ls);
    }

    @PutMapping("updateEtudiant")
    Etudiant updateEtudiant(@RequestBody Etudiant e){
        return  iEtudiantService.editEtudiant(e);
    }

    @DeleteMapping("DeleteByIDEtudiant/{id}")
    void DeleteEtudiantByID(@PathVariable("id") long id){
        iEtudiantService.deleteById(id);
    }

    @DeleteMapping("deleteEtudiant")
    void DeleteEtudiant (@RequestBody Etudiant e){
        iEtudiantService.deleteEtudiant(e);
    }

}
