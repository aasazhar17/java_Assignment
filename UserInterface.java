import java.util.Scanner;

// UserInterface class to handle user interactions
public class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner scanner;
    
    // Constructor
    public UserInterface() {
        accounts = new Account[10]; // Initial capacity of 10 accounts
        accountCount = 0;
        scanner = new Scanner(System.in);
    }
    
    // Method to create a new account
    public void createAccount() {
        if (accountCount >= accounts.length) {
            // Resize the array if needed
            Account[] newAccounts = new Account[accounts.length * 2];
            System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
            accounts = newAccounts;
        }
        
        System.out.println("\n--- Create New Account ---");
        
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        
        double initialDeposit = 0;
        while (true) {
            System.out.print("Enter initial deposit amount: ");
            try {
                initialDeposit = Double.parseDouble(scanner.nextLine());
                if (initialDeposit < 0) {
                    System.out.println("Initial deposit cannot be negative. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
        
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        Account newAccount = new Account(name, initialDeposit, email, phone);
        accounts[accountCount++] = newAccount;
        
        System.out.println("Account created successfully with Account Number: " + newAccount.getAccountNumber());
    }
    
    // Method to perform deposit
    public void performDeposit() {
        if (accountCount == 0) {
            System.out.println("No accounts exist. Please create an account first.");
            return;
        }
        
        System.out.println("\n--- Deposit Money ---");
        int accountNumber = getAccountNumberInput();
        
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        
        double amount = 0;
        while (true) {
            System.out.print("Enter amount to deposit: ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("Amount must be positive. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
        
        account.deposit(amount);
    }
    
    // Method to perform withdrawal
    public void performWithdrawal() {
        if (accountCount == 0) {
            System.out.println("No accounts exist. Please create an account first.");
            return;
        }
        
        System.out.println("\n--- Withdraw Money ---");
        int accountNumber = getAccountNumberInput();
        
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        
        double amount = 0;
        while (true) {
            System.out.print("Enter amount to withdraw: ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("Amount must be positive. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
        
        account.withdraw(amount);
    }
    
    // Method to show account details
    public void showAccountDetails() {
        if (accountCount == 0) {
            System.out.println("No accounts exist. Please create an account first.");
            return;
        }
        
        System.out.println("\n--- View Account Details ---");
        int accountNumber = getAccountNumberInput();
        
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        
        account.displayAccountDetails();
    }
    
    // Method to update contact details
    public void updateContact() {
        if (accountCount == 0) {
            System.out.println("No accounts exist. Please create an account first.");
            return;
        }
        
        System.out.println("\n--- Update Contact Details ---");
        int accountNumber = getAccountNumberInput();
        
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        
        System.out.print("Enter new email address: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();
        
        account.updateContactDetails(email, phone);
    }
    
    // Helper method to find account by account number
    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }
    
    // Helper method to get account number input
    private int getAccountNumberInput() {
        int accountNumber = 0;
        while (true) {
            System.out.print("Enter account number: ");
            try {
                accountNumber = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid account number. Please enter a valid number.");
            }
        }
        return accountNumber;
    }
    
    // Main menu method
    public void mainMenu() {
        int choice;
        
        do {
            System.out.println("\nWelcome to the Banking Application!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = 0; // Invalid choice
            }
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Thank you for using the Banking Application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
        } while (choice != 6);
    }
    
    // Main method to run the application
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}