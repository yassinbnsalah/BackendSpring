package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.EmailDetails;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.User;

public interface  IEmailService {
    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);
    void sendMailReservationInformation(Reservation r , User u);
    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
