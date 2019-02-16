package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public class DiamondAccount extends Account {

    private static final Double OVERDRAFT_LIMIT = 500.00;

    public DiamondAccount(AccountData accountData) {
        super(accountData);
    }

    @Override
    protected boolean canWithdraw(Double amount) {
        return getBalance() + OVERDRAFT_LIMIT >= amount;
    }
}