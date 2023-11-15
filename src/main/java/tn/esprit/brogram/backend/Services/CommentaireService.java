package tn.esprit.brogram.backend.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.Commentaire;
import tn.esprit.brogram.backend.DAO.Repositories.CommentaireRepository;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentaireService implements ICommentaireService{
    @Autowired
    private CommentaireRepository commentaireRepository;

    @Override
    public List<Commentaire> findAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire findById(long id) {
        return commentaireRepository.findById(id).orElse(null);
    }

    @Override
    public Commentaire addCommentaire(Commentaire commentaire) {
        commentaire.setCreatedAt(new Date());
        return commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> addAllComments(List<Commentaire> commentaires) {
        return commentaireRepository.saveAll(commentaires);
    }

    @Override
    public Commentaire editCommentaire(Commentaire commentaire) {
        commentaire.setUpdatedAt(new Date());
        return commentaireRepository.save(commentaire);
    }

    @Override
    public void deleteByID(long id) {
        commentaireRepository.deleteById(id);
    }

    @Override
    public void delete(Commentaire commentaire) {
        commentaireRepository.delete(commentaire);
    }
}
