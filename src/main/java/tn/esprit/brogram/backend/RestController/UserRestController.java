package tn.esprit.brogram.backend.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.Services.IUserService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("UserRestController")
public class UserRestController {
    IUserService iUserService ;

    @GetMapping("findUserByEmail/{email}")
    User getUserByEmail(@PathVariable("email") String email){
        return iUserService.findByEmail(email);
    }

    @GetMapping("findEtudiantByEcoleAndRole/{universite}/{role}")
    List<User> findEtudiantByEcoleAndRole(@PathVariable("universite") String universite , @PathVariable("role") Roles role){
        return iUserService.findEtudiantByEcoleAndRole(universite,role);
    }
}
