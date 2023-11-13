package tn.esprit.brogram.backend.Services;

import tn.esprit.brogram.backend.DAO.Entities.User;

public interface IUserService {
     User findByEmail(String email);
}
