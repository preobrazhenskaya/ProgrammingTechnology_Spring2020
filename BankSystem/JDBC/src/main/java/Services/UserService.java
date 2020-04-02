package Services;

import DB.Repositories.UserRepo;
import Models.User;

import java.util.UUID;

public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UUID createUser(String login, String password, String address, String phone) {
        User user = new User(UUID.randomUUID(), login, password, address, phone);
        try {
            userRepo.addUser(user);
        } catch (Exception e) {
            return null;
        }
        return user.getId();
    }

    public UUID loginByLogin(String login, String password) {
        try {
            User user = userRepo.getUserByLogin(login);
            if (!(user.getPassword().equals(password))) {
                return null;
            }
            return user.getId();
        } catch (Exception e) {
            return null;
        }
    }

    public UUID loginByPhone(String phone, String password) {
        try {
            User user = userRepo.getUserByPhone(phone);
            if (!(user.getPassword().equals(password))) {
                return null;
            }
            return user.getId();
        } catch (Exception e) {
            return null;
        }
    }
}
