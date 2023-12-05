package tn.esprit.brogram.backend.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="Demande")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDemande ;
    @Column(name="name")
    private String name;
    @Column(name="prename")
    private String prename ;
    @Column(name="email")
    private String email ;
    @Column(name="cin")
    private long cin ;

    @Column(name="ecole")
    private String ecole ;
    @Column(name="typeChamber")
    private TypeChamber typeChamber ;
    @Column(name="dateDemande")
    private LocalDate dateDemande ;
    @Column(name="AnneeUniversitaire")
    private String AnneeUniversitaire ;
    @Column(name="autoRenewed")
    private boolean autoRenewed ;
    @Column(name="createdAt")
    private Date createdAt ;
    @Column(name="updatedAt")
    private Date updatedAt ;
    @Column(name="state")
    private StateDemande state ;


}
