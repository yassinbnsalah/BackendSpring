package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Bloc;

import java.util.List;

public interface IBlocService {

    Bloc addBloc(Bloc b);
    List<Bloc> addAllBlocs(List<Bloc> ls) ;
    Bloc editBloc(Bloc b) ;
    List<Bloc> findAll();
    Bloc findById(long id);
    void deleteByID(long id);
    void delete(Bloc b) ;

    List<Bloc> findBlocByFoyer_IdFoyer(long idFoyer);
}
