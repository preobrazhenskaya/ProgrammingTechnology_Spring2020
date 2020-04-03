package ssu.BankSystemSpring.repository;

import org.springframework.data.repository.CrudRepository;
import ssu.BankSystemSpring.entity.Operation;

import java.util.List;

public interface OperationRepo extends CrudRepository<Operation, Long> {
    List<Operation>findByFromAccount(Long fromAccount);
}
