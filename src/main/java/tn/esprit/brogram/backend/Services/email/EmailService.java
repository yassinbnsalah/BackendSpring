package tn.esprit.brogram.backend.Services.email;

public interface EmailService  {
    String sendMail( String to, String subject, String body);
}
