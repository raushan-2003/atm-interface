import java.util.Scanner;

public class ATMSystem {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BankAccount account = new BankAccount(1000.0);
        ATM atm = new ATM(account, scanner);

        while (true) {
            atm.displayMenu();
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    atm.deposit();
                    break;
                case 3:
                    atm.withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using our ATM. Goodbye!\n\n");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }

            System.out.println(); 
        }
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = Math.max(initialBalance, 0);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited: %.2f\n", amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("Successfully withdrawn: %.2f\n", amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance for withdrawal.");
            return false;
        } else {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account, Scanner scanner) {
        this.account = account;
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("\n******************** ATM MENU ********************\n");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.print("\nSelect an option (1-4): ");
    }

    public void checkBalance() {
        System.out.printf("\nYour current balance is: Rs %.2f\n", account.getBalance());
    }

    public void deposit() {
        System.out.print("\nEnter amount to deposit: Rs ");
        
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    public void withdraw() {
        System.out.print("\nEnter amount to withdraw: Rs ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}
