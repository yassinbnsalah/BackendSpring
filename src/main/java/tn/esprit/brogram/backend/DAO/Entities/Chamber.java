package tn.esprit.brogram.backend.DAO.Entities;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import java.util.HashSet;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="chamber")
@Builder
public class Chamber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idChamber ;

    @Column(name="numeroChamber")
    private int numerochamber ;

    @Column(name="TypeC")
    private TypeChamber typeC ;

    @Column(name="Description")
    private String Description;

    @Column(name="Etat")
    private boolean Etat;

    @Column(name="CreatedAt")
    private Date CreatedAt;

    @Column(name="UpdatedAt")
    private Date UpdatedAt;
    @Lob
    @Column(name = "imagebyte", length = 100000)  // Adjust the length as needed
    private byte[] imagebyte;

    @JsonIgnore
    @ManyToOne
    Bloc bloc ;

    @OneToMany(cascade =  CascadeType.ALL)

    private  Set<Reservation> res  = new HashSet<>();

}
