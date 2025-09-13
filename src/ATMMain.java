import java.util.Scanner;

/**
 * Main class for ATM simulation
 * This provides a console-based user interface for the ATM system
 * Beginner-friendly implementation with clear menu options
 */
public class ATMMain {
    private static ATM atm;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        atm = new ATM();
        scanner = new Scanner(System.in);
        
        System.out.println("=================================");
        System.out.println("    Welcome to ATM Simulation    ");
        System.out.println("=================================");
        
        // Sample accounts information for testing
        System.out.println("\n--- Sample Accounts for Testing ---");
        System.out.println("Account: 123456, PIN: 1234 (John Doe - $1000)");
        System.out.println("Account: 789012, PIN: 5678 (Jane Smith - $2500)");
        System.out.println("Account: 345678, PIN: 9999 (Bob Johnson - $500)");
        System.out.println("-------------------------------------\n");
        
        // Main application loop
        while (true) {
            if (!atm.isLoggedIn()) {
                if (!loginUser()) {
                    continue;
                }
            }
            
            showMainMenu();
            int choice = getMenuChoice();
            processMenuChoice(choice);
        }
    }
    
    // Method to handle user login
    private static boolean loginUser() {
        System.out.println("\n=== ATM Login ===");
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        
        if (atm.authenticate(accountNumber, pin)) {
            System.out.println("\nLogin successful!");
            System.out.println("Welcome, " + atm.getCurrentAccountHolderName() + "!");
            return true;
        } else {
            System.out.println("\nInvalid account number or PIN. Please try again.");
            return false;
        }
    }
    
    // Method to display main menu
    private static void showMainMenu() {
        System.out.println("\n=== ATM Main Menu ===");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        System.out.print("Please select an option (1-5): ");
    }
    
    // Method to get user's menu choice
    private static int getMenuChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }
    
    // Method to process user's menu choice
    private static void processMenuChoice(int choice) {
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                withdrawMoney();
                break;
            case 4:
                logout();
                break;
            case 5:
                exitATM();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    // Method to check balance
    private static void checkBalance() {
        System.out.println("\n=== Balance Inquiry ===");
        double balance = atm.checkBalance();
        System.out.printf("Current Balance: $%.2f\n", balance);
    }
    
    // Method to deposit money
    private static void depositMoney() {
        System.out.println("\n=== Deposit Money ===");
        System.out.print("Enter amount to deposit: $");
        
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                System.out.println("Please enter a positive amount.");
                return;
            }
            
            if (atm.deposit(amount)) {
                System.out.printf("Successfully deposited $%.2f\n", amount);
                System.out.printf("New balance: $%.2f\n", atm.checkBalance());
            } else {
                System.out.println("Deposit failed. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }
    
    // Method to withdraw money
    private static void withdrawMoney() {
        System.out.println("\n=== Withdraw Money ===");
        System.out.printf("Current balance: $%.2f\n", atm.checkBalance());
        System.out.print("Enter amount to withdraw: $");
        
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                System.out.println("Please enter a positive amount.");
                return;
            }
            
            if (atm.withdraw(amount)) {
                System.out.printf("Successfully withdrew $%.2f\n", amount);
                System.out.printf("New balance: $%.2f\n", atm.checkBalance());
            } else {
                System.out.println("Withdrawal failed. Insufficient funds or invalid amount.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }
    
    // Method to logout
    private static void logout() {
        System.out.println("\nLogging out...");
        System.out.println("Thank you for using our ATM service!");
        atm.logout();
    }
    
    // Method to exit ATM
    private static void exitATM() {
        System.out.println("\nThank you for using our ATM service!");
        System.out.println("Goodbye!");
        scanner.close();
        System.exit(0);
    }
}