package lab01.example.model;

public class SimpleBankAccountWithAtm implements BankAccount {

    private static final int ATM_TRANSACTION_FEE = 1;

    private double balance;
    private final AccountHolder holder;

    public SimpleBankAccountWithAtm(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    @Override
    public AccountHolder getHolder() {
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int usrID, final double amount) {
        if (checkUser(usrID)) {
            this.balance += (amount - ATM_TRANSACTION_FEE);
        }
    }

    @Override
    public void withdraw(final int usrID, final double amount) {
        var totalAmount = amount + ATM_TRANSACTION_FEE;
        if (checkUser(usrID) && isWithdrawAllowed(totalAmount)) {
            this.balance -= totalAmount;
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
        return this.balance >= amount;
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }
}
