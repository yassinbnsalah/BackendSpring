package tn.esprit.brogram.backend.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.Rating;
import tn.esprit.brogram.backend.DAO.Entities.StateUniversite;
import tn.esprit.brogram.backend.DAO.Entities.Universite;
import tn.esprit.brogram.backend.DAO.Repositories.RatingRepository;
import tn.esprit.brogram.backend.DAO.Repositories.UniversiteRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class UniversiteService implements IUniversiteService{
    UniversiteRepository universiteRepository ;
    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public List<Universite> addAllUniversite(List<Universite> ls) {
        return universiteRepository.saveAll(ls);
    }

    @Override
    public Universite editUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public List<Universite> UnifindAll() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite UnifindById(long id) {
        return universiteRepository.findById(id).orElse(Universite.builder().build());
    }

    @Override
    public void UnideleteById(long id) {
        universiteRepository.deleteById(id);
    }

    @Override
    public void Unidelete(Universite u) {
        universiteRepository.delete(u);
    }

    @Override
    public Universite updateStatus(long id, String status) {
        Universite universite = universiteRepository.findById(id).orElse(Universite.builder().build());
        universite.setStatuts(status);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite findUniversiteByEmail(String email) {
        return universiteRepository.findUniversiteByEmail(email);
    }

    @Override
    public List<Universite> getAcceptedUniversites() {
        return universiteRepository.findByStatuts("Accepté");

    }

  /*  RatingRepository ratingRepository;
    @Override
    public Universite addRatingToUniversite(long universiteId, Rating rating) {
        Universite universite = universiteRepository.findById(universiteId)
                .orElseThrow(() -> new RuntimeException("Universite not found"));

        rating.setUniversite(universite);
        Rating savedRating = ratingRepository.save(rating);

        universite.getRatings().add(savedRating);
        universiteRepository.save(universite);

        return universite;    }

    @Override
    public Double calculateAverageRating(long universiteId) {
        Universite universite = universiteRepository.findById(universiteId)
                .orElseThrow(() -> new RuntimeException("Universite not found"));

        List<Rating> ratings = universite.getRatings();

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double sum = ratings.stream().mapToDouble(Rating::getStars).sum();
        return sum / ratings.size();    }*/


}
