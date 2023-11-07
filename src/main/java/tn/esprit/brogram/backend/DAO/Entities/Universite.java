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

    @Column(name="adress")
    private String adress ;

    @Column(name = "statuts")
    private String statuts;

    @OneToOne(cascade = CascadeType.ALL)
    private Foyer foyer ;
}
