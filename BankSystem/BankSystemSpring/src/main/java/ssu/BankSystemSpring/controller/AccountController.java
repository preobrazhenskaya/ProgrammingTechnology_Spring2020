package ssu.BankSystemSpring.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ApiOperation("Create account with provided currency")
    public ResponseEntity<Account> createAccount(@PathVariable("currency") String currency) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        if (!currency.equals("RUB") && !currency.equals("USD") && !currency.equals("EUR")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Account result = accountService.createAccount(currency, username);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping("/add/{account-id}")
    @ApiOperation("Add money to account")
    public ResponseEntity<Account> addMoney(@PathVariable("account-id") String accountId,
                            @RequestParam("currency") String currency,
                            @RequestParam("money") String money) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        if (!currency.equals("RUB") && !currency.equals("USD") && !currency.equals("EUR")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Account result = accountService.updateAccountMoney(Long.valueOf(accountId),
                    BigDecimal.valueOf(Integer.parseInt(money)), currency, MoneyOperation.ADD);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping("/transfer/{phone}")
    @ApiOperation("Transfer money by phone")
    public ResponseEntity<Account> transfer(@PathVariable("phone") String phone,
                            @RequestParam("account") String accountId,
                            @RequestParam("money") String money) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Account result = accountService.transfer(Long.valueOf(accountId), phone, BigDecimal.valueOf(Integer.parseInt(money)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation("Show all accounts by user")
    public ResponseEntity<List<Account>> showAll() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        return new ResponseEntity<>(accountService.getAccountByUser(username), HttpStatus.OK);
    }
}
