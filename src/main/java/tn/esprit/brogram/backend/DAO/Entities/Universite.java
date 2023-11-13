package tn.esprit.brogram.backend.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="universite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUniversite ;

    @Column(name="nomUniversite")
    private String nomUniversite ;

    @Column(name="adresse")
    private String adresse ;

    @Column(name = "statuts")
    private String statuts;

    @Column(name = "firstNameAgent")
    private String firstNameAgent;

    @Column(name = "lastNameAgent")
    private String lastNameAgent;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Foyer foyer ;
}
