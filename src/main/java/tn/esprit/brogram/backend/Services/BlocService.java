package tn.esprit.brogram.backend.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.Bloc;
import tn.esprit.brogram.backend.DAO.Repositories.BlocRepository;


import java.util.List;
@AllArgsConstructor
@Service
public class BlocService implements IBlocService{
    BlocRepository blocRepository ;
    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b); //ajouter many
    }

    @Override
    public List<Bloc> addAllBlocs(List<Bloc> ls) {
        return blocRepository.saveAll(ls);
    }

    @Override
    public Bloc editBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public List<Bloc> findAll() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc findById(long id) {

        return blocRepository.findById(id).orElse(Bloc.builder().idBloc(0).nomBloc("No Bloc Founded").build());

    }

    @Override
    public void deleteByID(long id) {
        blocRepository.deleteById(id);
    }

    @Override
    public void delete(Bloc b) {
        blocRepository.delete(b);
    }
    @Override
    public Bloc findBlocByChamber_IdChamber(long idChamber) {
        return blocRepository.findBlocByChambers_IdChamber(idChamber);
    }
    @Override
    public List<Bloc> findBlocByFoyer_IdFoyer(long idFoyer) {
        return blocRepository.findBlocByFoyer_IdFoyer(idFoyer);
    }
}
