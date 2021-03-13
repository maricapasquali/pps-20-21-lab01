package lab01.example;

import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.BankAccountFactory;
import lab01.example.model.BankAccountFactoryImpl;

public class Main {

    public static void main(String[] args) {
        final AccountHolder accountHolder = new AccountHolder("Mario", "Rossi", 1);
        final BankAccountFactory factory = new BankAccountFactoryImpl();
        final BankAccount bankAccount = factory.newSimpleBankAccount(accountHolder);

        bankAccount.deposit(accountHolder.getId(), 100);

        System.out.println("Current balance is " + bankAccount.getBalance());

        bankAccount.withdraw(accountHolder.getId(), 30);

        System.out.println("Current balance is " + bankAccount.getBalance());

        bankAccount.withdraw(accountHolder.getId(), 80);

        System.out.println("Current balance is " + bankAccount.getBalance());
    }
}
