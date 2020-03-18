package Part3.Phonebook;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    private static Scanner in = new Scanner(System.in);
    private Phonebook phonebook = new Phonebook();

    public void greeting() {
        System.out.println("=== Phonebook CLI ===");
        contactsMenu();
        chooseVar();
    }

    private void chooseVar() {
        System.out.println();
        System.out.println("Choose action: ");
        int k = in.nextInt();
        System.out.println();
        switch (k) {
            case 1:
                createPhonebook();
                chooseVar();
                break;
            case 2:
                addUser();
                chooseVar();
                break;
            case 3:
                deleteUser();
                chooseVar();
                break;
            case 4:
                changeUserName();
                chooseVar();
                break;
            case 5:
                changeUserPhone();
                chooseVar();
                break;
            case 6:
                showPhonebook();
                chooseVar();
                break;
            default:
                return;
        }
    }

    private void createPhonebook() {
        System.out.println("Enter count of users:");
        int count = in.nextInt();
        Map<String, String> newPhonebook = new HashMap<>();
        for (int i = 0; i < count; i++) {
            System.out.println("Enter name:");
            String name = in.next();
            System.out.println("Enter phone:");
            String phone = in.next();
            newPhonebook.put(name, phone);
        }
        phonebook = new Phonebook(newPhonebook);
    }

    private void addUser() {
        System.out.println("Enter name:");
        String name = in.next();
        System.out.println("Enter phone:");
        String phone = in.next();
        phonebook.addUser(name, phone);
    }

    private void deleteUser() {
        System.out.println("Enter name for delete:");
        String name = in.next();
        phonebook.deleteUser(name);
    }

    private void changeUserName() {
        System.out.println("Enter old name:");
        String oldName = in.next();
        System.out.println("Enter new name:");
        String newName = in.next();
        phonebook.changeName(oldName, newName);
    }

    private void changeUserPhone() {
        System.out.println("Enter name:");
        String name = in.next();
        System.out.println("Enter new phone:");
        String newPhone = in.next();
        phonebook.changePhone(name, newPhone);
    }

    private void showPhonebook() {
        phonebook.showPhonebook();
    }

    private void contactsMenu() {
        System.out.println("1 : Create Phonebook");
        System.out.println("2 : Add user");
        System.out.println("3 : Delete user");
        System.out.println("4 : Change name");
        System.out.println("5 : Change phone");
        System.out.println("6 : Show phonebook");
        System.out.println("other : exit");
    }
}
