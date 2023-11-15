package tn.esprit.brogram.backend.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Commentaire")
@Builder
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="auteur")
    private String auteur;
    @Column(name="contenu")
    private String contenu;
    @Column(name="Etat")
    private boolean Etat;

    @Column(name="CreatedAt")
    private Date CreatedAt;

    @Column(name="UpdatedAt")
    private Date UpdatedAt;
    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chamber chamber;
}
