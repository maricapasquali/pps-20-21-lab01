package lab01.example.model;

public interface BankAccountFactory {

    BankAccount newSimpleBankAccount(final AccountHolder accountHolder);

    BankAccount newSimpleBankAccount(final AccountHolder accountHolder, double balance);

    BankAccount newSimpleBankAccountWithAtm(final AccountHolder accountHolder);

    BankAccount newSimpleBankAccountWithAtm(final AccountHolder accountHolder, double balance);

}
