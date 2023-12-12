package tn.esprit.brogram.backend.DAO.Entities;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Foyer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long idFoyer ;

    @Column(name="nomFoyer")
    private String nomFoyer ;

    @Column(name="adresse")
    private String adresse ;

    @Column(name="description")
    private String description ;

    @Column(name="capaciteFoyer")
    private long capaciteFoyer ;

    @Column(name="Etat")
    private boolean Etat;

    @Column(name="CreatedAt")
    private Date CreatedAt;

    @Column(name="UpdatedAt")
    private Date UpdatedAt;

    @JsonIgnore
    @OneToOne(mappedBy = "foyer" )
    private Universite universite ;

    @OneToMany(mappedBy = "foyer")
    List<Bloc> blocs = new ArrayList<>();
    @Lob
    @Column(name = "imagebyte", length = 100000)  // Adjust the length as needed
    private byte[] imagebyte;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;
}
