import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest extends BaseSimpleBankAccountTest {

    @Override
    public BankAccount getBankAccount() {
        return bankAccountFactory.newSimpleBankAccount(new AccountHolder("Mario", "Rossi", 1));
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
        return isAndSucceedWithdraw ? 30 : 100;
    }
}