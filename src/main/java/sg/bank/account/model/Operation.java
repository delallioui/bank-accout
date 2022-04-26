package sg.bank.account.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Operation {
    private OperationType operationType;
    private LocalDate date;
    private BigDecimal amount;

    public Operation(OperationType operationType, LocalDate date, BigDecimal amount) {
        this.operationType = operationType;
        this.date = date;
        this.amount = amount;
    }
}
