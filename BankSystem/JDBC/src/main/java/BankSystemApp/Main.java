package BankSystemApp;

import DB.DBConnection;

public class Main {
    public static void main(String[] args) {
        if (DBConnection.createConnection() && DBConnection.createTables()) {
            CLI cli = new CLI();
            cli.greeting();
        } else {
            return;
        }
    }
}
