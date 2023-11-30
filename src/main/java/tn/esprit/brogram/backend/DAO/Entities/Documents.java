package tn.esprit.brogram.backend.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDocument;

    @Column(name = "documentType")
    private DocumentType documentType;

    @Lob
    @Column(name = "documentContent", length = 1000000)
    private byte[] documentContent;


    @ManyToOne
    private Universite universite;
}
