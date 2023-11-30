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



    //ByWiWi
    String getBlocNameById(long idBloc);
    //ByWiWi
    Bloc findBlocByChamber_IdChamber(long idChamber);

    //by wiwi
    boolean doesBlocExist(String nomBloc);

    List<Bloc> findBlocByFoyer_IdFoyer(long idFoyer);

}
