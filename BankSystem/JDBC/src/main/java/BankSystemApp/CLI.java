package BankSystemApp;

import DB.DBConnection;
import DB.Repositories.AccountRepo;
import DB.Repositories.OperationRepo;
import DB.Repositories.UserRepo;
import Helpers.CurrencyCode;
import Helpers.MoneyOperation;
import Models.Account;
import Models.Operation;
import Services.AccountService;
import Services.OperationService;
import Services.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CLI {
    private static Scanner in = new Scanner(System.in);

    private UserService userService;
    private AccountService accountService;
    private OperationService operationService;
    private UUID userID;

    public void greeting() {
        System.out.println("=== BANK SYSTEM JDBC ===");
        createHelpers();
        mainMenu();
    }

    private void createHelpers() {
        UserRepo userRepo = new UserRepo();
        userService = new UserService(userRepo);
        OperationRepo operationRepo = new OperationRepo();
        operationService = new OperationService(operationRepo);
        AccountRepo accountRepo = new AccountRepo();
        accountService = new AccountService(accountRepo, userService, operationService);
    }

    private void positiveMessage(String message) {
        System.out.println();
        System.out.println("Congratulations! " + message);
    }

    private void negativeMessage() {
        System.out.println();
        System.out.println("Oops! Something wrong.");
    }

    private void yourChoiceMessage() {
        System.out.println();
        System.out.print("Your choice: ");
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("=== MAIN MENU ===");
        System.out.println("1 : Registration");
        System.out.println("2 : Log in");
        System.out.println("other : Exit");
        yourChoiceMessage();
        int k = in.nextInt();
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
        System.out.println();
        System.out.println("=== REGISTRATION ===");
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
        System.out.println();
        System.out.println("=== LOG IN ===");
        System.out.println("1 : Log in by login");
        System.out.println("2 : Log in by phone");
        System.out.println("other : Back");
        yourChoiceMessage();
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
        System.out.println();
        System.out.println("=== LOG IN BY LOGIN ===");
        System.out.print("Enter login: ");
        String login = in.next();
        System.out.print("Enter password: ");
        String password = in.next();
        userID = userService.loginByLogin(login, password);
        if (userID != null) {
            positiveMessage("You are logged in!");
            userMenu();
        } else {
            negativeMessage();
            loginMenu();
        }
    }

    private void loginByPhone() {
        System.out.println();
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
        System.out.println();
        System.out.println("=== USER MENU ===");
        System.out.println("1 : Create account");
        System.out.println("2 : Add money");
        System.out.println("3 : Transfer");
        System.out.println("4 : History");
        System.out.println("other : Log out");
        yourChoiceMessage();
        int k = in.nextInt();
        switch (k) {
            case 1:
                createAccount();
                break;
            case 2:
                addMoney();
                break;
            case 3:
                transfer();
                break;
            case 4:
                showHistory();
                break;
            default:
                logOut();
                break;
        }
    }

    private CurrencyCode currencyCodeMenu() {
        System.out.println("Select currency:");
        System.out.println("1 : RUB");
        System.out.println("2 : EUR");
        System.out.println("3 : USD");
        System.out.println("other : Back");
        yourChoiceMessage();
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
                userMenu();
                break;
        }
        return currencyCode;
    }

    private void createAccount() {
        System.out.println();
        System.out.println("=== ACCOUNT CREATING ===");
        CurrencyCode currencyCode = currencyCodeMenu();
        if (accountService.createAccount(currencyCode, userID)) {
            positiveMessage("Account created!");
        } else {
            negativeMessage();
        }
        userMenu();
    }

    private Account accountMenu() {
        System.out.println("Your accounts:");
        List<Account> accounts = accountService.getAccountByUser(userID);
        for (int i = 0; i < accounts.size(); i ++) {
            Account account = accounts.get(i);
            Integer index = i + 1;
            System.out.println(index + " : Currency code - " + account.getAccCode() + ", Money - " + account.getAmount());
        }
        yourChoiceMessage();
        int k = in.nextInt();
        return accounts.get(k - 1);
    }

    private void newAccountInfo(Account account) {
        System.out.println("New account info:");
        System.out.println("Currency code - " + account.getAccCode() + ", Money - " + account.getAmount());
    }

    private void addMoney() {
        System.out.println();
        System.out.println("=== MONEY ADDING ===");
        UUID accountId = accountMenu().getId();
        System.out.println();
        System.out.print("Amount of money to add: ");
        BigDecimal money = in.nextBigDecimal();
        System.out.println();
        CurrencyCode currencyCode = currencyCodeMenu();
        Account updatedAccount = accountService.updateAccountMoney(accountId, money, currencyCode, MoneyOperation.ADD);
        if (updatedAccount != null) {
            positiveMessage("Money added!");
            newAccountInfo(updatedAccount);
        } else {
            negativeMessage();
        }
        userMenu();
    }

    private void transfer() {
        System.out.println();
        System.out.println("=== TRANSFER ===");
        Account accountFrom = accountMenu();
        System.out.println();
        System.out.print("Recipient's phone: ");
        String phoneTo = in.next();
        System.out.println();
        System.out.print("Amount of money to transfer: ");
        BigDecimal money = in.nextBigDecimal();
        Account updatedAccount = accountService.transfer(accountFrom, phoneTo, money);
        if (updatedAccount != null) {
            positiveMessage("Money transferred!");
            newAccountInfo(updatedAccount);
        } else {
            negativeMessage();
        }
        userMenu();
    }

    private void showHistory() {
        System.out.println();
        UUID accountId = accountMenu().getId();
        System.out.println();
        System.out.println("=== HISTORY ===");
        List<Operation> history = operationService.getOperationByAccount(accountId);
        for (Operation operation : history) {
            System.out.println(operation.getId() + ":");
            System.out.println("Date - " + operation.getDate());
            System.out.println("Currency - " + operation.getCurrency());
            System.out.println("From account - " + operation.getFromAccount());
            System.out.println("To account - " + operation.getToAccount());
            System.out.println("Amount - " + operation.getAmount());
            System.out.println("Amount before - " + operation.getAmountBefore());
            System.out.println("Amount after - " + operation.getAmountAfter());
            System.out.println();
        }
        userMenu();
    }

    private void logOut() {
        userID = null;
        mainMenu();
    }
}
