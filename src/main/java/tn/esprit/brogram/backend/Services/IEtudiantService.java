package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Etudiant;

import java.util.List;

public interface IEtudiantService {
    Etudiant addEtudiant(Etudiant e) ;
    List<Etudiant> addAllEtudiant(List<Etudiant> ls);
    Etudiant editEtudiant (Etudiant e);
    List<Etudiant> findAll() ;
    Etudiant findById(long id);
    void deleteById(long id);
    void deleteEtudiant(Etudiant e);
}
