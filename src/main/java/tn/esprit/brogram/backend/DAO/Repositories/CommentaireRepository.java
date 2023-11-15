package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

}
