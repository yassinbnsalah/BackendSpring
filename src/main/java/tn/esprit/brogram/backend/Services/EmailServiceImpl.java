package tn.esprit.brogram.backend.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import tn.esprit.brogram.backend.DAO.Entities.EmailDetails;

import org.thymeleaf.context.Context;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.User;

import java.io.File;
@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.username}") private String sender;

    public EmailServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    @Override
    public String sendSimpleMail(EmailDetails details) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            Context context = new Context() ;

            helper.setTo(details.getRecipient());
            helper.setSubject("Universite Process Information");
            String htmlContent = templateEngine.process("universiteMail", context);
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            System.out.println("message sended succefully");
        } catch (MessagingException e) {
            // Handle exception
            System.out.println(e.getMessage());

        }
        return "message sended succefully" ;
    }


    @Override
    public void sendMailReservationInformation(Reservation r, User u) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            Context context = new Context() ;
            context.setVariable("reference", r.getIdReservation());
            context.setVariable("username", u.getNomEt());

            helper.setTo(u.getEmail());
            helper.setSubject("Reservation Année Universitaire"+r.getDateDebut().getYear()+"-"+r.getDateFin().getYear());
            String htmlContent = templateEngine.process("testFile", context);
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            System.out.println("message sended succefully");
        } catch (MessagingException e) {
            // Handle exception
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}
