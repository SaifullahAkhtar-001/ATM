import java.util.HashMap;
import java.util.Scanner;

// User class representing bank account holders
class User {
    private String name;
    private String cardNumber;
    private String pin;

    public User(String name, String cardNumber, String pin) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }
}

// BankAccount class representing the user's account
class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}

// ATM class representing the ATM machine
class ATM {
    private HashMap<String, User> users;
    private HashMap<String, BankAccount> accounts;

    public ATM() {
        users = new HashMap<>();
        accounts = new HashMap<>();
    }

    public void addUser(User user, BankAccount account) {
        users.put(user.getCardNumber(), user);
        accounts.put(user.getCardNumber(), account);
    }

    public boolean verifyCredentials(String cardNumber, String pin) {
        if (users.containsKey(cardNumber)) {
            User user = users.get(cardNumber);
            return user.getPin().equals(pin);
        }
        return false;
    }

    public double checkBalance(String cardNumber) {
        if (accounts.containsKey(cardNumber)) {
            BankAccount account = accounts.get(cardNumber);
            return account.getBalance();
        }
        return -1; // Account not found
    }

    public void withdrawCash(String cardNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        
        if (accounts.containsKey(cardNumber)) {
            BankAccount account = accounts.get(cardNumber);
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                System.out.println("Cash withdrawn successfully.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
}

public class App {
    public static void main(String[] args) {
        // Create ATM instance
        ATM atm = new ATM();

        // Create user and account
        User user1 = new User("John Doe", "123456789", "1234");
        BankAccount account1 = new BankAccount("987654321", 1000.0);

        // Add user and account to ATM
        atm.addUser(user1, account1);

        // Simulate ATM operations
        String cardNumber = "123456789";
        String pin = "1234";

        if (atm.verifyCredentials(cardNumber, pin)) {
            double balance = atm.checkBalance(cardNumber);
            System.out.println("Current balance: $" + balance);

            atm.withdrawCash(cardNumber);

            balance = atm.checkBalance(cardNumber);
            System.out.println("Updated balance: $" + balance);
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}
