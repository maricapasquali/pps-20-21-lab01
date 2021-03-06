package lab01.example.model;

class SimpleBankAccountWithAtm extends SimpleBankAccount {

    private static final int ATM_TRANSACTION_FEE = 1;

    public SimpleBankAccountWithAtm(final AccountHolder holder, final double balance) {
        super(holder, balance);
    }

    @Override
    public void deposit(final int usrID, final double amount) {
        super.deposit(usrID, amount - ATM_TRANSACTION_FEE);
    }

    @Override
    public void withdraw(final int usrID, final double amount) {
        super.withdraw(usrID, amount + ATM_TRANSACTION_FEE);
    }
}
