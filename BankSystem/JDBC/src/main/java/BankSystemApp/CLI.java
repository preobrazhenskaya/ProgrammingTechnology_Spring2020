package BankSystemApp;

import DB.DBConnection;
import DB.Repositories.AccountRepo;
import DB.Repositories.UserRepo;
import Helpers.CurrencyCode;
import Helpers.MoneyOperation;
import Models.Account;
import Services.AccountService;
import Services.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CLI {
    private static Scanner in = new Scanner(System.in);

    private UserService userService;
    private AccountService accountService;
    private UUID userID;

    public void greeting() {
        System.out.println("=== BANK SYSTEM JDBC ===");
        createHelpers();
        mainMenu();
    }

    private void createHelpers() {
        UserRepo userRepo = new UserRepo();
        userService = new UserService(userRepo);
        AccountRepo accountRepo = new AccountRepo();
        accountService = new AccountService(accountRepo);
    }

    private void positiveMessage(String message) {
        System.out.println();
        System.out.print("Congratulations! " + message);
    }

    private void negativeMessage() {
        System.out.println();
        System.out.println("Oops! Something wrong.");
    }

    private void mainMenu() {
        System.out.println("1 : Registration");
        System.out.println("2 : Log in");
        System.out.println("other : exit");
        System.out.println();
        System.out.print("Your choice: ");
        int k = in.nextInt();
        System.out.println();
        switch (k) {
            case 1:
                registration();
                break;
            case 2:
                loginMenu();
                break;
            default:
                exitApp();
                break;
        }
    }

    private void registration() {
        System.out.print("=== REGISTRATION ===");
        System.out.print("Enter login: ");
        String login = in.next();
        System.out.print("Enter password: ");
        String password = in.next();
        System.out.print("Enter address: ");
        String address = in.next();
        System.out.print("Enter phone: ");
        String phone = in.next();
        userID = userService.createUser(login, password, address, phone);
        if (userID != null) {
            positiveMessage("You are registered!");
        } else {
            negativeMessage();
        }
        System.out.println();
        mainMenu();
    }

    private void loginMenu() {
        System.out.println("=== LOG IN ===");
        System.out.println("1 : Log in by login");
        System.out.println("2 : Log in by phone");
        System.out.println("other : Back");
        System.out.print("Your choice: ");
        int k = in.nextInt();
        switch (k) {
            case 1:
                loginByLogin();
                break;
            case 2:
                loginByPhone();
                break;
            default:
                mainMenu();
                break;
        }
    }

    private void loginByLogin() {
        System.out.println("=== LOG IN BY LOGIN ===");
        System.out.print("Enter login: ");
        String login = in.next();
        System.out.print("Enter password: ");
        String password = in.next();
        userID = userService.loginByLogin(login, password);
        if (userID != null) {
            positiveMessage("You are logged in!");
        } else {
            negativeMessage();
            loginMenu();
        }
    }

    private void loginByPhone() {
        System.out.println("=== LOG IN BY PHONE ===");
        System.out.print("Enter login: ");
        String login = in.next();
        System.out.print("Enter password: ");
        String password = in.next();
        userID = userService.loginByPhone(login, password);
        if (userID != null) {
            positiveMessage("You are logged in!");
            userMenu();
        } else {
            negativeMessage();
            loginMenu();
        }
    }

    private void exitApp() {
        DBConnection.closeConnection();
    }

    private void userMenu() {
        System.out.println("=== USER MENU ===");
        System.out.println("1 : Create account");
        System.out.println("2 : Add money");
        System.out.println("3 : Transfer");
        System.out.println("4 : History");
        System.out.println("other : Log out");
        System.out.print("Your choice: ");
        int k = in.nextInt();
        switch (k) {
            case 1:
                createAccount();
                break;
            case 2:
                addMoney();
                break;
            default:

                break;
        }
    }

    private void createAccount() {
        System.out.println("=== ACCOUNT CREATING ===");
        System.out.println("Select currency:");
        System.out.println("1 : RUB");
        System.out.println("2 : EUR");
        System.out.println("3 : USD");
        CurrencyCode currencyCode = null;
        int k = in.nextInt();
        switch (k) {
            case 1:
                currencyCode = CurrencyCode.RUB;
                break;
            case 2:
                currencyCode = CurrencyCode.EUR;
                break;
            case 3:
                currencyCode = CurrencyCode.USD;
                break;
            default:
                createAccount();
                break;
        }
        if (accountService.createAccount(currencyCode, userID)) {
            positiveMessage("Account created!");
        } else {
            negativeMessage();
        }
        userMenu();
    }

    private void addMoney() {
        System.out.println("=== MONEY ADDING ===");
        System.out.println("Select account:");
        List<Account> accounts = accountService.getAccountByUser(userID);
        for (int i = 0; i < accounts.size(); i ++) {
            Account account = accounts.get(i);
            Integer index = i + 1;
            System.out.println(index + " : Currency code - " + account.getAccCode() + ", Money - " + account.getAmount());
        }
        System.out.print("Your choice: ");
        int k = in.nextInt();
        UUID accountId = accounts.get(k - 1).getId();
        System.out.println("Amount of money to add:");
        BigDecimal money = in.nextBigDecimal();
        Account updatedAccount = accountService.updateAccountMoney(accountId, money, MoneyOperation.ADD);
        if (updatedAccount != null) {
            positiveMessage("Money added!");
            System.out.println("New account info:");
            System.out.println(k + " : Currency code - " + updatedAccount.getAccCode() + ", Money - " + updatedAccount.getAmount());
        } else {
            negativeMessage();
        }
        userMenu();
    }
}
