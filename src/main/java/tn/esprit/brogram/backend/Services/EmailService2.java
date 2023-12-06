package tn.esprit.brogram.backend.Services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.Services.email.EmailService;

@Service
public class EmailService2 implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    public EmailService2(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public String sendMail(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            javaMailSender.send(mimeMessage);

            return "mail send";

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    private final TemplateEngine templateEngine;
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
    }

