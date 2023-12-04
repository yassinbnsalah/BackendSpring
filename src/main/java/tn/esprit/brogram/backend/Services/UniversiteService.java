package tn.esprit.brogram.backend.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.*;
import tn.esprit.brogram.backend.DAO.Repositories.DocumentRepository;
import tn.esprit.brogram.backend.DAO.Repositories.FoyerRepository;
import tn.esprit.brogram.backend.DAO.Repositories.RatingRepository;
import tn.esprit.brogram.backend.DAO.Repositories.UniversiteRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UniversiteService implements IUniversiteService{
    UniversiteRepository universiteRepository ;
    FoyerRepository foyerRepository ;

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
    public List<Universite> UnifindAll() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite UnifindById(long id) {
        return universiteRepository.findById(id).orElse(Universite.builder().build());
    }

    @Override
    public void UnideleteById(long id) {
        universiteRepository.deleteById(id);
    }

    @Override
    public void Unidelete(Universite u) {
        universiteRepository.delete(u);
    }

    @Override
    public Universite updateStatus(long id, String status) {
        Universite universite = universiteRepository.findById(id).orElse(Universite.builder().build());
        universite.setStatuts(status);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite findUniversiteByEmail(String email) {
        return universiteRepository.findUniversiteByEmail(email);
    }

    @Override
    public List<Universite> getAcceptedUniversites() {
        return universiteRepository.findByStatuts("Accepté");

    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer f = foyerRepository.findById(idFoyer).get();
        Universite u = universiteRepository.findUnBynomUniversite(nomUniversite);
        u.setFoyer(f);
        universiteRepository.save(u);
        return u;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite u = universiteRepository.findById(idUniversite).get();
        u.setFoyer(null);
        universiteRepository.save(u);
        return u;    }

    /*@Override
    public Optional<Universite> getUniversiteWithStudentCount(long universiteId) {
        Optional<Universite> universiteOptional = universiteRepository.findByIdWithStudents(universiteId);

        universiteOptional.ifPresent(universite -> {
            long enrolledStudentCount = universite.getEnrolledStudentCount();
            System.out.println("Enrolled Student Count: " + enrolledStudentCount);
        });

        return universiteOptional;
    }*/

    @Override
    public Universite findUniversiteByNomUniversiteAndEmail(String name, String email) {
        return universiteRepository.findUniversiteByNomUniversiteAndEmail(name,email);
    }

    DocumentRepository documentRepository;
    @Override
    public Universite UnifindByNomUniv(String nomUniversite) {
        Universite universite = universiteRepository.findUnBynomUniversite(nomUniversite);
        return universite != null ? universite : Universite.builder().build();    }

    @Override
    public List<Documents> downloadDocs(long idUniversite) {
        return documentRepository.findByUniversiteIdUniversite(idUniversite);

    }

}
