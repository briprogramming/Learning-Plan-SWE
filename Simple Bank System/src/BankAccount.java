/*Define classes and encapsulation */

public class BankAccount {
    private String accountNumber;
    protected int balance;
    private String accountHolderName;

    public String accountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int balance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String accountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("You do not have any money.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds, not enough money.");
        }
    }

    public static class SavingsAccounts extends BankAccount {
        private double interestRate;

        public double getInterestRate() {
            return interestRate;
        }

        public void setInterestRate(double interestRate) {
            this.interestRate = interestRate;
        }
    }

    public static class CheckingAccount extends BankAccount {
        private double overdraftLimit;

        public double getOverdraftLimit() {
            return overdraftLimit;
        }

        public void setOverdraftLimit(double overdraftLimit) {
            this.overdraftLimit = overdraftLimit;
        }

        @Override
        public void withdraw(double amount) {
            if (amount > 0 && amount <= (balance + overdraftLimit)) {
                balance -= amount;
            } else {
                System.out.println("Insufficient funds, not enough money.");
            }
        }
    }

    public static void main(String[] args) {
        SavingsAccounts savingsAccount = new SavingsAccounts();
        savingsAccount.setAccountNumber("321-123456");
        savingsAccount.setBalance(1);
        savingsAccount.setAccountHolderName("Mary Lamb");
        savingsAccount.setInterestRate(2.50);

        // Use DepositTransaction
        DepositTransaction depositTransaction = new DepositTransaction();
        depositTransaction.processTransaction(savingsAccount, 500); // Deposit $500

        System.out.println("Account Number: " + savingsAccount.accountNumber());
        System.out.println("Account Holder Name: " + savingsAccount.accountHolderName());
        System.out.println("Balance: " + savingsAccount.balance());
        System.out.println("Interest Rate: " + savingsAccount.getInterestRate() + "%");
    }
}

abstract class TransactionManager {
    public abstract void processTransaction(BankAccount account, double amount);
}

class DepositTransaction extends TransactionManager {
    @Override
    public void processTransaction(BankAccount account, double amount) {
        account.deposit(amount);
        System.out.println("Deposited: " + amount);
    }
}



