package tn.esprit.brogram.backend.DAO.Entities;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="Etudiant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "idEtudiant")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEtudiant ;

    @Column(name="nomEt")
    private String nomEt ;
    @Column(name="prenomEt")
    private String prenomEt;
    @Column(name="cin")
    private long cin ;
    @Column(name="ecole")
    private String ecole ;
    @Column(name="dateNaissance")
    private LocalDate dateNaissance ;


    @ManyToMany(mappedBy = "etudiants" , cascade =  CascadeType.ALL)
    private Set<Reservation> reservations ;
}
