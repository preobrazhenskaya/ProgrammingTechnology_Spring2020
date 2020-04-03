package ssu.BankSystemSpring.repository;

import org.springframework.data.repository.CrudRepository;
import ssu.BankSystemSpring.entity.Account;

import java.util.List;

public interface AccountRepo extends CrudRepository<Account, Long> {
    List<Account> findByClientId(Long clientId);
}
