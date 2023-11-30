package tn.esprit.brogram.backend.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.brogram.backend.DAO.Entities.EmailDetails;
import tn.esprit.brogram.backend.Services.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;




    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        String status = emailService.sendSimpleMail(details);

        return status;
    }
}
