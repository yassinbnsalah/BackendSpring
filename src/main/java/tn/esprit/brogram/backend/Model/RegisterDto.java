package tn.esprit.brogram.backend.Model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Random;
import java.util.random.RandomGenerator;

@ToString
@Data
public class RegisterDto {
    private String nomEt;
    private String prenomEt;
    private long cin;
    private String ecole;
    private LocalDate dateNaissance ;

    private String email;
    private String password;
}
