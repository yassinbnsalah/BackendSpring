package tn.esprit.brogram.backend.RestController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Demande;
import tn.esprit.brogram.backend.DAO.Entities.StateDemande;
import tn.esprit.brogram.backend.Services.IDemandeService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("DemandeRestController")
public class DemandeRestController {
    @Autowired
    IDemandeService iDemandeService;

    @PostMapping("createdemande")
    Demande CreateDemande(@RequestBody Demande demande){
        return iDemandeService.CreateDemande(demande);
    }


    @PutMapping("updatedemande/{state}/{id}")
    Demande UpdateDemande(@PathVariable("state") StateDemande state ,@PathVariable("id") int id){

        return iDemandeService.UpdateDemande(state,id) ;
    }
    @GetMapping("listedemandebyUniversite/{universite}")
    List<Demande> ListeDemandeByUniversite(@PathVariable("universite") String universite){
        return iDemandeService.ListeDemandeByUniversite(universite) ;
    }

    @GetMapping("listedemandebyEmail/{email}")
    List<Demande> listeDemandeByEmail(@PathVariable("email") String email){
        return iDemandeService.ListeDemandeByEmail(email);
    }

}
