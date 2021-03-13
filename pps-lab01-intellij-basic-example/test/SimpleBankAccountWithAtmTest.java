import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the SimpleBankAccountWithAtmTest implementation
 */
class SimpleBankAccountWithAtmTest extends BaseSimpleBankAccountTest {

    @Override
    public BankAccount getBankAccount() {
        return bankAccountFactory.newSimpleBankAccountWithAtm(new AccountHolder("Anna", "Verdi", 1321));
    }

    @Override
    public double getDepositAmount() {
        return 100;
    }

    @Override
    public double getWithdrawAmount() {
        return 70;
    }

    @Override
    public double getExpectedAmount(final boolean isAndSucceedWithdraw) {
        return isAndSucceedWithdraw ? 28 : 99;
    }

    @Test
    void testWithdrawMoreAmount() {
        super.bankAccount.deposit(super.accountHolder.getId(), 100);
        super.bankAccount.withdraw(super.accountHolder.getId(), 150);
        assertEquals(getExpectedAmount(false), super.bankAccount.getBalance());
    }
}