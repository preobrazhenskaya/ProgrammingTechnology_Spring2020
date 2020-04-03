package ssu.BankSystemSpring.service;

import org.springframework.stereotype.Service;
import ssu.BankSystemSpring.entity.Account;
import ssu.BankSystemSpring.entity.User;
import ssu.BankSystemSpring.helpers.MoneyConvert;
import ssu.BankSystemSpring.helpers.MoneyOperation;
import ssu.BankSystemSpring.repository.AccountRepo;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    private AccountRepo accountRepo;
    private UserService userService;
    private OperationService operationService;

    public AccountService(AccountRepo accountRepo, UserService userService, OperationService operationService) {
        this.accountRepo = accountRepo;
        this.userService = userService;
        this.operationService = operationService;
    }

    public Account createAccount(String currencyCode, String username) {
        User user = userService.getUserByUsername(username);
        Account account = new Account(user.getId(), BigDecimal.valueOf(0), currencyCode);
        accountRepo.save(account);
        return account;
    }

    public Account getAccountById(Long accountId) {
        return accountRepo.findById(accountId).get();
    }

    public List<Account> getAccountByUser(String username) {
        User user = userService.getUserByUsername(username);
        return accountRepo.findByClientId(user.getId());
    }

    public Account updateAccountMoney(Long accountId, BigDecimal money, String currencyCode, MoneyOperation operation) {
        Account account = accountRepo.findById(accountId).get();
        if (!currencyCode.equals(account.getAccCode())) {
            money = MoneyConvert.convert(currencyCode, account.getAccCode(), money);
        }
        BigDecimal newAmount;
        if (operation == MoneyOperation.ADD) {
            newAmount = account.getAmount().add(money);
        } else {
            newAmount = account.getAmount().subtract(money);
        }
        account.setAmount(newAmount);
        accountRepo.save(account);
        return account;
    }

    public Account transfer(Long fromAccountId, String phoneTo, BigDecimal money) {
        Account accountFrom = getAccountById(fromAccountId);
        User user = userService.getUserByPhone(phoneTo);
        Account accountTo = getAccountByUser(user.getUsername()).get(0);
        Account updateAccountFrom = updateAccountMoney(accountFrom.getId(), money, accountFrom.getAccCode(), MoneyOperation.REDUCE);
        Account updateAccountTo = updateAccountMoney(accountTo.getId(), money, accountFrom.getAccCode(), MoneyOperation.ADD);
        if (operationService.createOperation(accountTo.getAccCode(), accountFrom.getId(), accountTo.getId(), money, accountFrom.getAmount(), updateAccountFrom.getAmount())) {
            return updateAccountFrom;
        } else {
            return null;
        }
    }
}
