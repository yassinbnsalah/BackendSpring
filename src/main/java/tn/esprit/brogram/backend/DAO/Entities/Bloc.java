package tn.esprit.brogram.backend.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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

    @JsonIgnore
    @ManyToOne
    Foyer foyer ;

    @OneToMany(mappedBy = "bloc" , cascade = CascadeType.ALL)
    private Set<Chamber> chambers;

    @Column(name = "created_at",nullable = true,updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    private String description;

    private String status;
}