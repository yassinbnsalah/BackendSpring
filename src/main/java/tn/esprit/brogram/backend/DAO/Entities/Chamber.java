package tn.esprit.brogram.backend.DAO.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    Bloc bloc ;
    @JsonIgnore
    @OneToMany(cascade =  CascadeType.ALL)
    private  Set<Reservation> res  ;
}
