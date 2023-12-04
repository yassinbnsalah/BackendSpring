package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Bloc;
import tn.esprit.brogram.backend.DAO.Entities.TypeChamber;

import java.util.List;
import java.util.Map;

public interface IBlocService {

    Bloc addBloc(Bloc b);
    List<Bloc> addAllBlocs(List<Bloc> ls) ;
    Bloc editBloc(Bloc b) ;
    List<Bloc> findAll();
    Bloc findById(long id);
    void deleteByID(long id);
    void delete(Bloc b) ;

    List<Bloc> findBlocByFoyer_IdFoyer(long idFoyer);
    double calculateAverageCapacity(long blocId);
    List<Object[]> countChambersByType(long blocId);

}
