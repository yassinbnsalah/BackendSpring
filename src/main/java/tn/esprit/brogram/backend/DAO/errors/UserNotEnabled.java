package tn.esprit.brogram.backend.DAO.errors;

public class UserNotEnabled extends RuntimeException{
    private static final long serialVerisionUID = 2;

    public UserNotEnabled(String message) {
        super(message);
    }
}
