package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Commentaire;

import java.util.List;

public interface ICommentaireService {
    List<Commentaire> findAll();

    Commentaire findById(long id);

    Commentaire addCommentaire(Commentaire commentaire);

    List<Commentaire> addAllComments(List<Commentaire> commentaires);

    Commentaire editCommentaire(Commentaire commentaire);

    void deleteByID(long id);

    void delete(Commentaire commentaire);
}
