package tn.esprit.brogram.backend.Services.email;

import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.User;

public interface EmailService  {
    String sendMail( String to, String subject, String body);

    void sendMailReservationInformation(Reservation r , User u);
}
