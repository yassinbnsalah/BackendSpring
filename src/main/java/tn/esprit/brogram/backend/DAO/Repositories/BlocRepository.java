package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Bloc;

import java.util.List;

public interface BlocRepository extends JpaRepository<Bloc,Long> {

    List<Bloc> findBlocByFoyer_IdFoyer(long idFoyer);
}
