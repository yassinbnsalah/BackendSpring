package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.brogram.backend.DAO.Entities.Bloc;
import tn.esprit.brogram.backend.DAO.Entities.TypeChamber;

import java.util.List;
import java.util.Map;

public interface BlocRepository extends JpaRepository<Bloc,Long> {

    List<Bloc> findBlocByFoyer_IdFoyer(long idFoyer);
    @Query("SELECT c.typeC, COUNT(c) FROM Bloc b JOIN b.chambers c WHERE b.idBloc = :blocId GROUP BY c.typeC")
    List<Object[]> countChambersByType(long blocId);


}
