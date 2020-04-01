package Part3.Phonebook;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    private Map<String, String> phonebook = new HashMap<String, String>();

    public Phonebook() {}

    public Phonebook(Map<String, String> phonebook) {
        this.phonebook = phonebook;
    }

    public void addUser(String name, String phone) {
        phonebook.put(name, phone);
    }

    public void deleteUser(String name) {
        phonebook.remove(name);
    }

    public void changeName(String oldName, String newName) {
        String phone = phonebook.get(oldName);
        deleteUser(oldName);
        addUser(newName, phone);
    }

    public void changePhone(String name, String newPhone) {
        addUser(name, newPhone);
    }

    public void showPhonebook() {
        System.out.println("=== Phonebook ===");
        phonebook.forEach((name, phone) -> System.out.println("Name: " + name + ", Phone: " + phone));
    }
}
