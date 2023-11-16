package tn.esprit.brogram.backend.DAO.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Foyer;
import tn.esprit.brogram.backend.DAO.Entities.Image;
public interface ImageRepositroy extends JpaRepository<Image,Long>{
}
