package tn.esprit.brogram.backend.Model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String email;
    private String password;

}
