package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.Roles;
import tn.esprit.brogram.backend.DAO.Entities.User;

import java.util.List;

public interface IUserService {
     User findByEmail(String email);

     List<User> findEtudiantByEcoleAndRole(String schoolName, Roles role);
}
