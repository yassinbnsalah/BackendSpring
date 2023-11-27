package tn.esprit.brogram.backend.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.Foyer;
import tn.esprit.brogram.backend.DAO.Entities.Universite;
import tn.esprit.brogram.backend.DAO.Repositories.FoyerRepository;
import tn.esprit.brogram.backend.DAO.Repositories.UniversiteRepository;

import java.util.Date;
import java.util.List;
import java.util.List;

@AllArgsConstructor
@Service
public class FoyerService implements IFoyerService {
    FoyerRepository foyerRepository;
    UniversiteRepository universiteRepository;

    @Override
    public Foyer AddFoyer(Foyer f,String name) {
        Universite u = universiteRepository.findUnBynomUniversite(name);
        u.setFoyer(f);
        f.setCreatedAt(new Date());
        universiteRepository.save(u);
        return foyerRepository.save(f);
    }

    @Override
    public List<Foyer> AddAllFoyer(List<Foyer> ls) {
        return foyerRepository.saveAll(ls);
    }

    @Override
    public Foyer editFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public List<Foyer> findAllFoyer() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer findByIDFoyer(long id) {
        return foyerRepository.findById(id).orElse(Foyer.builder().build());
    }

    @Override
    public void DeleteByIDFoyer(long id) {
        foyerRepository.deleteById(id);
    }

    @Override
    public List<Foyer> findFoyerByUniversersite(String nom) {
        return foyerRepository.findFoyerByUniversiteNomUniversite(nom);
    }

    @Override
    public void deleteFoyer(Foyer f) {
        foyerRepository.delete(f);

    }
    @Override
    public void updateEtatById(long idFoyer, boolean newEtat) {

        foyerRepository.updateEtatById(idFoyer, newEtat);

    }
}
