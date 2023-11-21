package tn.esprit.brogram.backend.DAO.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float stars;

    @ManyToOne
    @JoinColumn(name = "universite_id", nullable = false)
    private Universite universite;
}
