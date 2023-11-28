package tn.esprit.brogram.backend.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Chamber;
import tn.esprit.brogram.backend.DAO.Entities.Reservation;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.Model.ChangePasswordRequest;
import tn.esprit.brogram.backend.Model.LoginResponce;
import tn.esprit.brogram.backend.Services.AuthService;
import tn.esprit.brogram.backend.Services.IUserService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("UserRestController")
public class UserRestController {
    IUserService iUserService ;
    private final AuthService authService;


    @GetMapping("findUserByEmail/{email}")
    User getUserByEmail(@PathVariable("email") String email){
        return iUserService.findByEmail(email);
    }

    @GetMapping("findEtudiantByEcoleAndRole/{universite}/{role}")
    List<User> findEtudiantByEcoleAndRole(@PathVariable("universite") String universite , @PathVariable("role") Roles role){
        return iUserService.findEtudiantByEcoleAndRole(universite,role);
    }

    @PutMapping("updateUser")
    User editUser( @RequestBody User user){
        return iUserService.updateUser(user);
    }



    @PostMapping("/change-password")
    public LoginResponce changePassword(@RequestBody ChangePasswordRequest request) {
        if (request.getEmail() == null ||
                request.getOldPass().isEmpty() ||
                request.getNewPass() == null) {
            System.out.println("Email, old password, and new password are required");
        }
        return authService.changePassword(request.getEmail(), request.getOldPass(), request.getNewPass());
    }

    @GetMapping("/findUsers")
    List<User> getUsers(){
        return iUserService.getEtudiantUsers();
    }


    @PutMapping("toggelUser")
    User enableOrDisable( @RequestParam String email){
        return iUserService.enableOrDisable(email);
    }


}
