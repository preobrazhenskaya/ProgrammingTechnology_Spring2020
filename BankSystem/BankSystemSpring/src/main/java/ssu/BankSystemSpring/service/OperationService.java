package ssu.BankSystemSpring.service;

import org.springframework.stereotype.Service;
import ssu.BankSystemSpring.entity.Operation;
import ssu.BankSystemSpring.repository.OperationRepo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OperationService {
    private OperationRepo operationRepo;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public OperationService(OperationRepo operationRepo) {
        this.operationRepo = operationRepo;
    }

    public boolean createOperation(String currencyCode, Long accountFrom, Long accountTo, BigDecimal money, BigDecimal moneyBefore, BigDecimal moneyAfter) {
        Date date = new Date();
        Operation operation = new Operation(dateFormat.format(date), currencyCode, accountFrom, accountTo, money, moneyBefore, moneyAfter);
        try {
            operationRepo.save(operation);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Operation> getOperationByAccount(Long fromAccount) {
        try {
            return operationRepo.findByFromAccount(fromAccount);
        } catch (Exception e) {
            return null;
        }
    }
}
