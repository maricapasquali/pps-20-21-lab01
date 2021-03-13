import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.BankAccountFactory;
import lab01.example.model.BankAccountFactoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class BaseSimpleBankAccountTest {

    protected double initialAmount = 0;
    protected double wrongAmount = 50;
    protected int wrongId = -1;

    protected static BankAccountFactory bankAccountFactory;
    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;

    protected abstract BankAccount getBankAccount();

    protected abstract double getDepositAmount();

    protected abstract double getWithdrawAmount();

    protected abstract double getExpectedAmount(final boolean isAndSucceedWithdraw);

    @BeforeAll
    static void beforeAll() {
        bankAccountFactory = new BankAccountFactoryImpl();
    }

    @BeforeEach
    void beforeEach() {
        bankAccount = getBankAccount();
        accountHolder = bankAccount.getHolder();
        wrongId = accountHolder.getId() + 1;
    }

    @Test
    void testInitialBalance() {
        assertEquals(initialAmount, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), getDepositAmount());
        assertEquals(getExpectedAmount(false), bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), getDepositAmount());
        bankAccount.deposit(wrongId, wrongAmount);
        assertEquals(getExpectedAmount(false), bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), getDepositAmount());
        bankAccount.withdraw(accountHolder.getId(), getWithdrawAmount());
        assertEquals(getExpectedAmount(true), bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), getDepositAmount());
        bankAccount.withdraw(wrongId, wrongAmount);
        assertEquals(getExpectedAmount(false), bankAccount.getBalance());
    }

}
