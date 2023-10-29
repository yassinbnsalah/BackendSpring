package tn.esprit.brogram.backend.DAO.Entities;

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


    @OneToOne
    private Foyer foyer ;
}
