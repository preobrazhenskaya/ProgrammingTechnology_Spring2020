package Services;

import DB.Repositories.AccountRepo;
import Helpers.MoneyConvert;
import Helpers.MoneyOperation;
import Models.Account;
import Helpers.CurrencyCode;
import Models.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class AccountService {
    private AccountRepo accountRepo;
    private UserService userService;
    private OperationService operationService;

    public AccountService(AccountRepo accountRepo, UserService userService, OperationService operationService) {
        this.accountRepo = accountRepo;
        this.userService = userService;
        this.operationService = operationService;
    }

    public boolean createAccount(CurrencyCode currencyCode, UUID clientId) {
        Account account = new Account(UUID.randomUUID(), clientId, BigDecimal.valueOf(0), currencyCode);
        try {
            accountRepo.addAccount(account);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Account> getAccountByUser(UUID userId) {
        List<Account> result;
        try {
            result = accountRepo.getAccountsByUser(userId);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    public Account updateAccountMoney(UUID id, BigDecimal money, CurrencyCode currencyCode, MoneyOperation operation) {
        try {
            Account account = accountRepo.getAccountById(id);
            if (currencyCode != account.getAccCode()) {
                money = MoneyConvert.convert(currencyCode, account.getAccCode(), money);
            }
            BigDecimal newAmount;
            if (operation == MoneyOperation.ADD) {
                newAmount = account.getAmount().add(money);
            } else {
                newAmount = account.getAmount().subtract(money);
            }
            account.updateAmount(newAmount);
            accountRepo.updateAccountAmount(account);
            return account;
        } catch (Exception e) {
            return null;
        }
    }

    public Account transfer(Account accountFrom, String phoneTo, BigDecimal money) {
        try {
            User user = userService.getUserByPhone(phoneTo);
            Account accountTo = getAccountByUser(user.getId()).get(0);
            Account updateAccountFrom = updateAccountMoney(accountFrom.getId(), money, accountFrom.getAccCode(), MoneyOperation.REDUCE);
            Account updateAccountTo = updateAccountMoney(accountTo.getId(), money, accountFrom.getAccCode(), MoneyOperation.ADD);
            if (operationService.createOperation(accountTo.getAccCode(), accountFrom.getId(), accountTo.getId(), money, accountFrom.getAmount(), updateAccountFrom.getAmount())) {
                return updateAccountFrom;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
