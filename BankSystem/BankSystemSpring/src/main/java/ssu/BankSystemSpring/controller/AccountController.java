package ssu.BankSystemSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ssu.BankSystemSpring.entity.Account;
import ssu.BankSystemSpring.helpers.MoneyOperation;
import ssu.BankSystemSpring.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create/{currency}")
    public boolean createAccount(@PathVariable("currency") String currency) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        if (!currency.equals("RUB") && !currency.equals("USD") && !currency.equals("EUR")) {
            return false;
        } else {
            accountService.createAccount(currency, username);
            return true;
        }
    }

    @PostMapping("/add/{account-id}")
    public boolean addMoney(@PathVariable("account-id") String accountId,
                            @RequestParam("currency") String currency,
                            @RequestParam("money") String money) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        if (!currency.equals("RUB") && !currency.equals("USD") && !currency.equals("EUR")) {
            return false;
        } else {
            accountService.updateAccountMoney(Long.valueOf(accountId), BigDecimal.valueOf(Integer.parseInt(money)),
                    currency, MoneyOperation.ADD);
            return true;
        }
    }

    @PostMapping("/transfer/{phone}")
    public boolean transfer(@PathVariable("phone") String phone,
                            @RequestParam("account") String accountId,
                            @RequestParam("money") String money) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        accountService.transfer(Long.valueOf(accountId), phone, BigDecimal.valueOf(Integer.parseInt(money)));
        return true;
    }

    @GetMapping("/all")
    public List<Account> showAll() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        return accountService.getAccountByUser(username);
    }
}
