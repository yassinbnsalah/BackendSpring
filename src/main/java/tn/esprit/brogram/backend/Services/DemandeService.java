package tn.esprit.brogram.backend.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.*;
import tn.esprit.brogram.backend.DAO.Repositories.ChamberRepository;
import tn.esprit.brogram.backend.DAO.Repositories.DemandeRepository;
import tn.esprit.brogram.backend.DAO.Repositories.ReservationRepository;
import tn.esprit.brogram.backend.DAO.Repositories.UserRepository;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Service
public class DemandeService implements IDemandeService{
    DemandeRepository demandeRepository  ;
    ChamberRepository chamberRepository ;
    UserRepository userRepository ;
    ReservationService reservationService ;
    UserService userService ;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Demande CreateDemande(Demande demande) {
        demande.setState(StateDemande.EnCour);
        demande.setCreatedAt(new Date());
        demande.setDateDemande(LocalDate.now());
        return demandeRepository.save(demande);
    }

    ReservationRepository reservationRepository ;
    @Override
    public Demande UpdateDemande(StateDemande state , int id ) {


        Demande demande = demandeRepository.findById(id).get() ;

        demande.setUpdatedAt(new Date());
        User user = userService.findByEmail(demande.getEmail());
        List<Reservation> reservationsValidated = reservationRepository.findReservationByEtudiants_email(user.getEmail());
        boolean hadReservation = false;
        if(!reservationsValidated.isEmpty()){
            for (Reservation reservation : reservationsValidated) {
                if((reservation.getAnneeUniversitaire().equals(demande.getAnneeUniversitaire()))&&
                        (reservation.getEstValide())){
                    hadReservation = true ;
                    System.out.println("this user 3ando Reservation BRO LEAVE US ");
                    break;
                }
            }
        }
        if(hadReservation){
            demande.setState(state);
            if(state == StateDemande.Confirmer){
                System.out.println("Searching Chamber ");

                List<Chamber> chambers = chamberRepository.findChamberByTypeCAndAndBlocFoyerUniversiteNomUniversite(demande.getTypeChamber() , demande.getEcole()) ;
                System.out.println("Looping Chamber");
                boolean test = false ;

                for (Chamber chamber : chambers) {
                    Set<Reservation> reservations = chamber.getRes() ;
                    int i = (int) reservations.stream().filter(Reservation::getEstValide).count();
                    if(chamber.getTypeC().equals(TypeChamber.Simple)&&i==0){
                        test = true ;
                    }else if (chamber.getTypeC().equals(TypeChamber.Double) && i<=1){
                        test = true ;
                    }else if (chamber.getTypeC().equals(TypeChamber.Triple) && i<=2){
                        test = true ;
                    }
                    System.out.println("Chamber num"+chamber);
                    if(test){
                        //User user = userService.findByEmail(demande.getEmail());
                        if(user.getId()==0L){
                            User newuser = new User();
                            newuser.setNomEt(demande.getName());
                            newuser.setPrenomEt(demande.getPrename());
                            newuser.setEcole(demande.getEcole());
                            newuser.setCin(demande.getCin());
                            newuser.setEmail(demande.getEmail());
                            newuser.setPassword(passwordEncoder.encode(demande.getEmail()));
                            newuser.setRole(Roles.ETUDIANT);
                            userRepository.save(newuser);
                        }

                        List<Long> CIN = new ArrayList<>();
                        CIN.add(demande.getCin());
                        reservationService.addReservation(chamber.getNumerochamber(),CIN);
                        break;
                    }
                }
                if(!test){
                    demande.setState(StateDemande.Refuser);
                }
            }
        }else{
            demande.setState(StateDemande.Refuser);
        }
        return demandeRepository.save(demande);
    }

    @Override
    public List<Demande> ListeDemandeByUniversite(String universite) {
        return demandeRepository.findDemandeByEcole(universite);
    }

    @Override
    public List<Demande> ListeDemandeByEmail(String email) {
        return demandeRepository.findDemandeByEmail(email);
    }
}
