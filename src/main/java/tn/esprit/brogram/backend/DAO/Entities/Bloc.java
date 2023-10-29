package tn.esprit.brogram.backend.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name="Bloc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bloc {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long idBloc ;
    @Column(name="nomBloc")
    private String nomBloc ;
    @Column(name="capaciteBloc")
    private int capaciteBloc ;

    @ManyToOne
    Foyer foyer ;


    @OneToMany(mappedBy = "bloc" , cascade = CascadeType.ALL)
    private Set<Chamber> chambers;
}
