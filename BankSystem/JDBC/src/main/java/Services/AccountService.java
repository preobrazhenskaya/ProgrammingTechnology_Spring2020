package Services;

import DB.Repositories.AccountRepo;
import Helpers.MoneyOperation;
import Models.Account;
import Helpers.CurrencyCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountService {
    private AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
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

    public Account updateAccountMoney(UUID id, BigDecimal money, MoneyOperation operation) {
        try {
            Account account = accountRepo.getAccountById(id);
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
}
