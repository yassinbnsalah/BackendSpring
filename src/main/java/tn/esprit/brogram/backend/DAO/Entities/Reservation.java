package tn.esprit.brogram.backend.DAO.Entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Reservation {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idReservation;

    @Column(name="anneeReservation")
    private Date anneeReservation ;

    @Column(name="estValide")
    private Boolean estValide ;

    @Column(name="dateDebut")
    private Date dateDebut ;

    @Column(name="dateFin")
    private Date dateFin  ;

    @Column(name="status")
    private StateReservation status ;

    @ManyToMany(cascade = CascadeType.ALL)
    public Set<User> etudiants ;


}
