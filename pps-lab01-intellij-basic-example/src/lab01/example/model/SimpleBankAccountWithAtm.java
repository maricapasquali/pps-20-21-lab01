package lab01.example.model;

class SimpleBankAccountWithAtm extends SimpleBankAccount implements BankAccountATM {

    private static final int ATM_TRANSACTION_FEE = 1;

    public SimpleBankAccountWithAtm(final AccountHolder holder, final double balance) {
        super(holder, balance);
    }

    @Override
    public void depositWithATM(int usrID, double amount) {
        if(isDepositAllowed(amount)){
            super.deposit(usrID, amount - ATM_TRANSACTION_FEE);
        }
    }

    @Override
    public void withdrawWithATM(int usrID, double amount) {
        super.withdraw(usrID, amount + ATM_TRANSACTION_FEE);
    }

    private boolean isDepositAllowed(final double amount){
        return amount > ATM_TRANSACTION_FEE;
    }
}
