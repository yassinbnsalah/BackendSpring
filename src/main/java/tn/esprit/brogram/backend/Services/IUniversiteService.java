package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Universite;

import java.util.List;

public interface IUniversiteService {
    Universite addUniversite(Universite u);
    List<Universite> addAllUniversite(List<Universite> ls);
    Universite editUniversite(Universite u);
    List<Universite> UnifindAll();
    Universite UnifindById(long id);
    void UnideleteById(long id);
    void Unidelete(Universite u);

}
