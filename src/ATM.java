import java.util.ArrayList;
import java.util.List;

/**
 * ATM class handles all ATM operations and manages accounts
 * This is a beginner-friendly implementation for ATM simulation
 */
public class ATM {
    private List<Account> accounts;
    private Account currentAccount;
    
    // Constructor
    public ATM() {
        accounts = new ArrayList<>();
        currentAccount = null;
        // Add some sample accounts for testing
        initializeSampleAccounts();
    }
    
    // Initialize some sample accounts for demonstration
    private void initializeSampleAccounts() {
        accounts.add(new Account("123456", "1234", "John Doe", 1000.0));
        accounts.add(new Account("789012", "5678", "Jane Smith", 2500.0));
        accounts.add(new Account("345678", "9999", "Bob Johnson", 500.0));
    }
    
    // Method to authenticate user with account number and PIN
    public boolean authenticate(String accountNumber, String pin) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.verifyPin(pin)) {
                currentAccount = account;
                return true;
            }
        }
        return false;
    }
    
    // Method to check balance
    public double checkBalance() {
        if (currentAccount != null) {
            return currentAccount.getBalance();
        }
        return -1; // Error case
    }
    
    // Method to deposit money
    public boolean deposit(double amount) {
        if (currentAccount != null && amount > 0) {
            return currentAccount.deposit(amount);
        }
        return false;
    }
    
    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (currentAccount != null && amount > 0) {
            return currentAccount.withdraw(amount);
        }
        return false;
    }
    
    // Method to get current account holder name
    public String getCurrentAccountHolderName() {
        if (currentAccount != null) {
            return currentAccount.getAccountHolderName();
        }
        return null;
    }
    
    // Method to get current account number
    public String getCurrentAccountNumber() {
        if (currentAccount != null) {
            return currentAccount.getAccountNumber();
        }
        return null;
    }
    
    // Method to logout
    public void logout() {
        currentAccount = null;
    }
    
    // Method to check if user is logged in
    public boolean isLoggedIn() {
        return currentAccount != null;
    }
}