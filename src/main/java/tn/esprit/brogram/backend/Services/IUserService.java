package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Etudiant;
import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.Model.LoginResponce;

import java.util.Date;
import java.util.List;

public interface IUserService {
     User findByEmail(String email);
     User updateUser (User user);
     List<User> findEtudiantByEcoleAndRole(String schoolName, Roles role);
     void changePassword(String email, String newPassword, String oldPssword);
     List<User> getEtudiantUsers();
     User enableOrDisable(String id);
     void saveVerificationToken(long id,String verfi);
     User findByVerificationToken(String verificationToken);
     void disableInactiveAccounts();
}
