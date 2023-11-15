package tn.esprit.brogram.backend.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.brogram.backend.DAO.Entities.Commentaire;

import tn.esprit.brogram.backend.DAO.Repositories.CommentaireRepository;
import tn.esprit.brogram.backend.Services.ICommentaireService;


import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("CommentaireRestController")
public class CommentaireController {
    @Autowired
    ICommentaireService iCommentaireService ;
    @Autowired
    CommentaireRepository commentaireRepo;

    @GetMapping("findAllComments")
    List<Commentaire> findAll(){
        return iCommentaireService.findAll();
    }

    @GetMapping("findCommentaireByID/{id}")
    Commentaire findCommentaireByID(@PathVariable("id") long id){
        return iCommentaireService.findById(id);
    }
    @PostMapping("addCommentaire")
    Commentaire addCommentaire(@RequestBody Commentaire cm){
        cm.setCreatedAt(new Date());
        return iCommentaireService.addCommentaire(cm);

    }


    @PostMapping("/addAllComments")
    List<Commentaire> AddAllComments(@RequestBody List<Commentaire> ls){
        return iCommentaireService.addAllComments(ls);
    }
    @PutMapping("updateCommentaire")
    Commentaire editCommentaire(@RequestBody Commentaire cm){

        cm.setUpdatedAt(new Date());
        return iCommentaireService.editCommentaire(cm);
    }

    @DeleteMapping("deleteCommentaireById/{id}")
    void DeleteCommentaireByID(@PathVariable("id") long id){

        iCommentaireService.deleteByID(id);
    }

    @DeleteMapping("deleteCommentaire")
    void DeleteCommentaire(@RequestBody Commentaire cm){
        iCommentaireService.delete(cm);
    }
}

