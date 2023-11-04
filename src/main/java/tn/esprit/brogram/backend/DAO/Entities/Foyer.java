package tn.esprit.brogram.backend.DAO.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
    private  String idFoyer ;
    @Column(name="nomFoyer")
    private String nomFoyer ;

    @Column(name="capaciteFoyer")
    private long capaciteFoyer ;

   @OneToOne(mappedBy = "foyer" , cascade = CascadeType.ALL)
    private Universite universite ;

    @OneToMany(mappedBy = "foyer" , cascade =  CascadeType.ALL)
    List<Bloc> blocs ;
}
