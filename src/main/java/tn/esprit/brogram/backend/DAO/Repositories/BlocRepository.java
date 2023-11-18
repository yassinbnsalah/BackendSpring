package tn.esprit.brogram.backend.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.brogram.backend.DAO.Entities.Bloc;

import java.util.List;

public interface BlocRepository extends JpaRepository<Bloc,Long> {
    //ByWiWi
    Bloc getBlocByNomBloc(String nomBlog);
    //ByWiWi
    Bloc findBlocByChambers_IdChamber(long idChamber);

}
