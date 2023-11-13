package tn.esprit.brogram.backend.Model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponce {
    private final String[] role;
    private final String accessToken;
}
