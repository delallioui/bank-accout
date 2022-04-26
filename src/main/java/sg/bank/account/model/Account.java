package sg.bank.account.model;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

public class Account {
    private BigDecimal balance;

    public Account(BigDecimal initialAmount) {
        checkAmount(initialAmount);
        this.balance = initialAmount;
    }

    public BigDecimal deposit(BigDecimal amount) {
        if (isNull(amount) || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Deposit amount can not be negative or null");
        }
        this.balance = this.balance.add(amount);
        return this.balance;
    }

    public BigDecimal withDraw(BigDecimal amount) {
        if (isNull(amount) || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("WithDraw amount can not be negative or null");
        }
        this.balance = this.balance.subtract(amount);
        return this.balance;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    private void checkAmount(BigDecimal initialAmount) {
        if (initialAmount == null || initialAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial amount can not be negative or null");
        }
    }
}
