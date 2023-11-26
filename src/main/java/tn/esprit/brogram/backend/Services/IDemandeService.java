package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Demande;
import tn.esprit.brogram.backend.DAO.Entities.StateDemande;

import java.util.List;

public interface IDemandeService {
    Demande CreateDemande(Demande demande);
    Demande UpdateDemande(StateDemande state , int id);

    List<Demande> ListeDemandeByUniversite(String universite);

    List<Demande> ListeDemandeByEmail(String email);

}
