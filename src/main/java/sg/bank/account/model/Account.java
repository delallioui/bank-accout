package sg.bank.account.model;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;

    public Account(BigDecimal initialAmount) {
        checkAmount(initialAmount, "Initial amount can not be negative or null");
        this.balance = initialAmount;
    }

    public BigDecimal deposit(BigDecimal amount) {
        checkAmount(amount, "Deposit amount can not be negative or null");
        this.balance = this.balance.add(amount);
        return this.balance;
    }

    public BigDecimal withDraw(BigDecimal amount) {
        checkAmount(amount, "WithDraw amount can not be negative or null");
        this.balance = this.balance.subtract(amount);
        return this.balance;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    private void checkAmount(BigDecimal initialAmount, String message) {
        if (initialAmount == null || initialAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
