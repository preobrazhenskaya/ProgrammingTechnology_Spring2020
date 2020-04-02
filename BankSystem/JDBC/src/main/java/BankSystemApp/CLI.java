package BankSystemApp;

import DB.DBConnection;
import DB.Repositories.UserRepo;
import Models.User;
import Services.UserService;

import java.util.Scanner;

public class CLI {
    private static Scanner in = new Scanner(System.in);

    public void greeting() {
        System.out.println("=== BANK SYSTEM JDBC ===");
        mainMenu();
        mainMenuVar();
    }

    public void positiveMessage(String message) {
        System.out.println();
        System.out.print("Congratulations! " + message);
    }

    public void negativeMessage() {
        System.out.println();
        System.out.println("Oops! Something wrong.");
    }

    private void mainMenu() {
        System.out.println("1 : Registration");
        System.out.println("2 : Log in");
        System.out.println("other : exit");
    }

    private void mainMenuVar() {
        UserService userService = createUserHelpers();
        System.out.println();
        System.out.print("Your choice: ");
        int k = in.nextInt();
        System.out.println();
        switch (k) {
            case 1:
                registration(userService);
                break;
            case 2:
                loginMenu(userService);
                break;
            default:
                exit();
                break;
        }
    }

    private UserService createUserHelpers() {
        UserRepo userRepo = new UserRepo();
        return new UserService(userRepo);
    }

    private void registration(UserService userService) {
        System.out.print("=== REGISTRATION ===");
        System.out.print("Enter login: ");
        String login = in.next();
        System.out.print("Enter password: ");
        String password = in.next();
        System.out.print("Enter address: ");
        String address = in.next();
        System.out.print("Enter phone: ");
        String phone = in.next();
        if (userService.createUser(login, password, address, phone)) {
            positiveMessage("You are registered!");
        } else {
            negativeMessage();
        }
        System.out.println();
        mainMenu();
    }

    private void loginMenu(UserService userService) {
        System.out.println("=== LOG IN ===");
        System.out.println("1 : Log in by login");
        System.out.println("2 : Log in by phone");
        System.out.print("Your choice: ");
        int k = in.nextInt();
        switch (k) {
            case 1:
                loginByLogin(userService);
                break;
            case 2:
                loginByPhone(userService);
                break;
            default:
                break;
        }
    }

    private void loginByLogin(UserService userService) {
        System.out.print("Enter login: ");
        String login = in.next();
        System.out.print("Enter password: ");
        String password = in.next();
        if (userService.loginByLogin(login, password)) {
            positiveMessage("You are logged in!");
        } else {
            negativeMessage();
            loginMenu(userService);
        }
    }

    private void loginByPhone(UserService userService) {
        System.out.print("Enter login: ");
        String login = in.next();
        System.out.print("Enter password: ");
        String password = in.next();
        if (userService.loginByPhone(login, password)) {
            positiveMessage("You are logged in!");
        } else {
            negativeMessage();
            loginMenu(userService);
        }
    }

    private void exit() {
        DBConnection.closeConnection();
    }
}
