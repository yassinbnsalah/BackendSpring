package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Foyer;

import java.util.List;

public interface IFoyerService {
    Foyer AddFoyer(Foyer f,String name);
    List<Foyer> AddAllFoyer(List<Foyer> ls);
    Foyer editFoyer(Foyer f);
    List<Foyer> findAllFoyer();
    Foyer findByIDFoyer(long id);
    void DeleteByIDFoyer(long id);
    List<Foyer> findFoyerByUniversersite(String nom);
    void deleteFoyer(Foyer f);
    void updateEtatById(long idFoyer, boolean newEtat);
}
