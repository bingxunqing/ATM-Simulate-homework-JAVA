# ATM-Simulate-homework-JAVA

A simple ATM simulation program written in Java for beginners learning Java programming.

## Description

This is a console-based ATM simulation that allows users to:
- Login with account number and PIN
- Check account balance
- Deposit money
- Withdraw money (with insufficient funds protection)
- Logout safely

## Features

- **User Authentication**: Secure login with account number and PIN verification
- **Balance Inquiry**: Check current account balance
- **Deposit Money**: Add money to the account with real-time balance updates
- **Withdraw Money**: Withdraw money with insufficient funds protection
- **User-Friendly Interface**: Clear console menus and prompts
- **Error Handling**: Proper validation for invalid inputs and operations

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line terminal

### How to Run

1. **Compile the Java files:**
   ```bash
   javac -d . src/*.java
   ```

2. **Run the ATM simulation:**
   ```bash
   java ATMMain
   ```

### Sample Accounts for Testing

The program comes with three pre-configured test accounts:

| Account Number | PIN  | Account Holder | Initial Balance |
|---------------|------|----------------|-----------------|
| 123456        | 1234 | John Doe       | $1000.00        |
| 789012        | 5678 | Jane Smith     | $2500.00        |
| 345678        | 9999 | Bob Johnson    | $500.00         |

## Usage Example

1. Run the program and you'll see the welcome screen
2. Enter an account number (e.g., `123456`)
3. Enter the corresponding PIN (e.g., `1234`)
4. Choose from the main menu options:
   - `1` - Check Balance
   - `2` - Deposit Money
   - `3` - Withdraw Money
   - `4` - Logout
   - `5` - Exit

## Project Structure

```
src/
├── Account.java    # Account class with balance and PIN management
├── ATM.java        # ATM class handling all banking operations
└── ATMMain.java    # Main class with user interface
```

## Learning Objectives

This project demonstrates basic Java concepts:
- Object-Oriented Programming (OOP)
- Classes and Objects
- Methods and Constructors
- Data Encapsulation
- User Input Handling
- Console I/O Operations
- Basic Error Handling

## Notes

This is a beginner-friendly implementation designed for educational purposes. The code includes:
- Clear comments and documentation
- Simple, readable code structure
- Basic security practices
- Error handling for common scenarios

Perfect for students who are just starting to learn Java programming!