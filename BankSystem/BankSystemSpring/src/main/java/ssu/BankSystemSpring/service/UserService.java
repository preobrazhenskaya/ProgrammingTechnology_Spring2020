package ssu.BankSystemSpring.service;

import org.springframework.stereotype.Service;
import ssu.BankSystemSpring.entity.User;
import ssu.BankSystemSpring.repository.UserRepo;

@Service
public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepository) {
        this.userRepo = userRepository;
    }

    public void createUser(String username, String password, String phone, String address) {
        User user = new User(username, password, phone, address);
        userRepo.save(user);
    }

    public User getUserByPhone(String phone) {
        return userRepo.findByPhone(phone);
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public boolean existsUserByUsername(String username) {
        return userRepo.existsUserByUsername(username);
    }
}
