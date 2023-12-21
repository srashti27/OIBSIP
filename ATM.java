import java.util.Scanner;

class User {
    private String userId;
    private String userPin;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
    }

    public boolean authenticate(String userId, String userPin) {
        return this.userId.equals(userId) && this.userPin.equals(userPin);
    }
}

class Account {
    private double balance;
    private String transactionHistory;

    public Account() {
        this.balance = 0.0;
        this.transactionHistory = "Transaction History:\n";
    }

    public double getBalance() {
        return balance;
    }

    public String getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory += "Deposit: +" + amount + "\n";
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory += "Withdrawal: -" + amount + "\n";
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory += "Transfer to " + recipient + ": -" + amount + "\n";
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Set up user and account
        User user = new User("123456", "7890");
        Account account = new Account();

        // Authenticate user
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.nextLine();

        if (user.authenticate(userId, userPin)) {
            System.out.println("Authentication successful. Welcome!");

            while (true) {
                // Display menu
                System.out.println("\nATM Menu:");
                System.out.println("1. View Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. View Transaction History");
                System.out.println("6. Quit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Balance: $" + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        account.withdraw(withdrawalAmount);
                        break;
                    case 4:
                        System.out.print("Enter transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        System.out.print("Enter recipient's User ID: ");
                        String recipientUserId = scanner.next();
                        // For simplicity, assuming the recipient has the same account structure
                        Account recipientAccount = new Account();
                        account.transfer(recipientAccount, transferAmount);
                        break;
                    case 5:
                        System.out.println(account.getTransactionHistory());
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }
}
