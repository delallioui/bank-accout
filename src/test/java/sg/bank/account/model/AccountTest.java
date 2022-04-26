package sg.bank.account.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {
    @Test
    void should_not_be_able_to_create_account_with_null_initial_amount() {
        assertThrows(IllegalArgumentException.class, () -> new Account(null));
    }

    @Test
    void should_not_be_able_to_create_account_with_negative_initial_amount() {
        assertThrows(IllegalArgumentException.class, () -> new Account(new BigDecimal("-123")));
    }

    @Test
    void should_get_112_balance_given_initial_amount_100_and_deposit_12() {
        // given
        Account account = new Account(new BigDecimal("100"));
        // when
        account.deposit(new BigDecimal("12"));
        // then
        assertEquals(new BigDecimal("112"), account.getBalance());
    }

    @Test
    void should_get_80_balance_given_initial_amount_100_and_deposit_20() {
        // given
        Account account = new Account(new BigDecimal("100"));
        // when
        account.withDraw(new BigDecimal("20"));
        // then
        assertEquals(new BigDecimal("80"), account.getBalance());
    }

    @Test
    void should_not_be_able_to_deposit_negative_amounts() {
        // given
        Account account = new Account(new BigDecimal("100"));
        // then
        assertThrows(IllegalArgumentException.class, () -> account.deposit(new BigDecimal("-20")));
    }

    @Test
    void should_not_be_able_to_withDraw_negative_amounts() {
        // given
        Account account = new Account(new BigDecimal("100"));
        // then
        assertThrows(IllegalArgumentException.class, () -> account.withDraw(new BigDecimal("-20")));
    }
}