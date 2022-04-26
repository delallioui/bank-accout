package sg.bank.account.service;

import org.junit.jupiter.api.Test;
import sg.bank.account.model.Account;
import sg.bank.account.model.AccountOperation;
import sg.bank.account.model.Operation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static sg.bank.account.model.OperationType.DEPOSIT;
import static sg.bank.account.model.OperationType.WITHDRAW;

class AccountOperationsPrinterTest {
    @Test
    void format() {
        AccountOperationsPrinter accountOperationsPrinter = new AccountOperationsPrinter();
        ArrayList<Operation> operations = new ArrayList<>(
                asList(
                        new Operation(WITHDRAW, LocalDate.of(2022, 4, 24), new BigDecimal("23.30")),
                        new Operation(DEPOSIT, LocalDate.of(2022, 4, 25), new BigDecimal("100.33")),
                        new Operation(WITHDRAW, LocalDate.of(2022, 4, 26), new BigDecimal("15.62")),
                        new Operation(WITHDRAW, LocalDate.of(2022, 4, 26), new BigDecimal("11.12"))
                ));
        String format = accountOperationsPrinter.format(new AccountOperation(new Account(new BigDecimal("55.06")), operations));
        assertThat(format).isEqualTo(
                "2022-04-24 - WITHDRAW - 23.30" + System.lineSeparator() +
                        "2022-04-25 - DEPOSIT - 100.33" + System.lineSeparator() +
                        "2022-04-26 - WITHDRAW - 15.62" + System.lineSeparator() +
                        "2022-04-26 - WITHDRAW - 11.12" + System.lineSeparator() +
                        "Balance 55.06");
    }
}