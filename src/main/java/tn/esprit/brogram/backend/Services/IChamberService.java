package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Chamber;

import java.util.List;

public interface IChamberService {
    Chamber addChamber(Chamber c);
    List<Chamber> addAllChambers(List<Chamber> ls);
    Chamber editChamber(Chamber c);
    List<Chamber> findAll() ;
    Chamber findById(long id);
    void deleteByID(long id);
    void delete(Chamber c);
}