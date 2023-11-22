package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Rating;
import tn.esprit.brogram.backend.DAO.Entities.StateUniversite;
import tn.esprit.brogram.backend.DAO.Entities.Universite;

import java.util.List;
import java.util.Optional;

public interface IUniversiteService {
    Universite addUniversite(Universite u);
    List<Universite> addAllUniversite(List<Universite> ls);
    Universite editUniversite(Universite u);
    List<Universite> UnifindAll();
    Universite UnifindById(long id);
    void UnideleteById(long id);
    void Unidelete(Universite u);
    Universite updateStatus(long id, String status);
    Universite findUniversiteByEmail(String email);
    List<Universite> getAcceptedUniversites();

   // Universite addRatingToUniversite(long universiteId, Rating rating);

 //   Double calculateAverageRating(long universiteId);

    Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) ;
    Universite desaffecterFoyerAUniversite(long idUniversite);
    Optional<Universite> getUniversiteWithStudentCount(long universiteId);

}
