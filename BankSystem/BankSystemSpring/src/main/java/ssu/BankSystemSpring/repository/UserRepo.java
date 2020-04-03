package ssu.BankSystemSpring.repository;

import ssu.BankSystemSpring.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByPhone(String phone);
    boolean existsUserByUsername(String username);
    boolean existsUserByPhone(String phone);
}
