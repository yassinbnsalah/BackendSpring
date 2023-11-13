package tn.esprit.brogram.backend.Services;



import org.springframework.stereotype.Service;
import tn.esprit.brogram.backend.DAO.Entities.User;
import tn.esprit.brogram.backend.DAO.Repositories.UserRepository;


@Service
//@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }
}
