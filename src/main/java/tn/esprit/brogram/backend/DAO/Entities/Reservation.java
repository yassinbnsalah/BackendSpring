package tn.esprit.brogram.backend.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    public Set<Etudiant> etudiants ;
}
