package lab01.example.model;

public class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    public BankAccount newSimpleBankAccount(AccountHolder accountHolder) {
        return newSimpleBankAccount(accountHolder, 0);
    }

    @Override
    public BankAccount newSimpleBankAccount(AccountHolder accountHolder, double balance) {
        return new SimpleBankAccount(accountHolder, balance);
    }

    @Override
    public BankAccount newSimpleBankAccountWithAtm(AccountHolder accountHolder) {
        return newSimpleBankAccountWithAtm(accountHolder, 0);
    }

    @Override
    public BankAccount newSimpleBankAccountWithAtm(AccountHolder accountHolder, double balance) {
        return new SimpleBankAccountWithAtm(accountHolder, balance);
    }
}
