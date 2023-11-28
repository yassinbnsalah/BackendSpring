package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
