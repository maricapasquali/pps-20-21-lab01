import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.BankAccountATM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the SimpleBankAccountWithAtmTest implementation
 */
class SimpleBankAccountWithAtmTest extends BaseSimpleBankAccountTest {

    private static final double AMOUNT1 = 100;
    private static final double AMOUNT2 = 70;
    private static final double AMOUNT3 = 99;

    @Override
    public BankAccount getBankAccount() {
        return bankAccountFactory.newSimpleBankAccountWithAtm(new AccountHolder("Anna", "Verdi", 1321));
    }

    @Override
    public double getDepositAmount() {
        return AMOUNT1;
    }

    @Override
    public double getWithdrawAmount() {
        return AMOUNT2;
    }

    @Override
    public double getExpectedAmount(final boolean isAndSucceedWithdraw) {
        return isAndSucceedWithdraw ? 30 : AMOUNT1;
    }

    @Test
    void testWithdrawATMMoreAmount() {
        ((BankAccountATM)super.bankAccount).depositWithATM(super.accountHolder.getId(), AMOUNT1);
        ((BankAccountATM)super.bankAccount).withdrawWithATM(super.accountHolder.getId(), AMOUNT1+AMOUNT2);
        assertEquals(AMOUNT3, super.bankAccount.getBalance());
    }

    @Test
    void testWithdrawATMEqualsBalance() {
        ((BankAccountATM)super.bankAccount).depositWithATM(super.accountHolder.getId(), AMOUNT1);
        ((BankAccountATM)super.bankAccount).withdrawWithATM(super.accountHolder.getId(), AMOUNT1);
        assertEquals(AMOUNT3, super.bankAccount.getBalance());
    }

    @Test
    void testDepositATMLessThanFee() {
        ((BankAccountATM)super.bankAccount).depositWithATM(super.accountHolder.getId(), 0.5);
        assertEquals(0, super.bankAccount.getBalance());
    }
}