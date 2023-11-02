package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Universite;
import tn.esprit.brogram.backend.DAO.Repositories.UniversiteRepository;

import java.util.List;

public class UniversiteService implements IUniversiteService{
    UniversiteRepository universiteRepository ;
    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public List<Universite> addAllUniversite(List<Universite> ls) {
        return universiteRepository.saveAll(ls);
    }

    @Override
    public Universite editUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public List<Universite> findAll() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite findById(long id) {
        return universiteRepository.findById(id).orElse(Universite.builder().build());
    }

    @Override
    public void deleteById(long id) {
        universiteRepository.deleteById(id);
    }

    @Override
    public void delete(Universite u) {
        universiteRepository.delete(u);
    }
}
