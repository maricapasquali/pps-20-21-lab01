package lab01.example.model;

public interface BankAccountATM extends BankAccount {

    void depositWithATM(int usrID, double amount);

    void withdrawWithATM(int usrID, double amount);
}
