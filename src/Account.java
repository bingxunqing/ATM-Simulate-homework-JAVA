/**
 * Account class represents a bank account with basic functionalities
 * This is a beginner-friendly implementation for ATM simulation
 */
public class Account {
    private String accountNumber;
    private String pin;
    private double balance;
    private String accountHolderName;
    
    // Constructor to create a new account
    public Account(String accountNumber, String pin, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }
    
    // Method to verify PIN
    public boolean verifyPin(String inputPin) {
        return this.pin.equals(inputPin);
    }
    
    // Method to get account balance
    public double getBalance() {
        return balance;
    }
    
    // Method to deposit money
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
    
    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    // Getter methods
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
}