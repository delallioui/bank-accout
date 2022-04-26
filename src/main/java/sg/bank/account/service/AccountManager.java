package sg.bank.account.service;

import sg.bank.account.model.Account;
import sg.bank.account.model.Operation;
import sg.bank.account.repository.AccountRepository;
import sg.bank.account.repository.OperationHistoryRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static sg.bank.account.model.OperationType.DEPOSIT;
import static sg.bank.account.model.OperationType.WITHDRAW;

public class AccountManager {
    private AccountRepository accountRepository;
    private OperationHistoryRepository operationHistoryRepository;

    public AccountManager(AccountRepository accountRepository, OperationHistoryRepository operationHistoryRepository) {
        this.accountRepository = accountRepository;
        this.operationHistoryRepository = operationHistoryRepository;
    }

    public void withDraw(Account account, BigDecimal amount) {
        account.withDraw(amount);
        accountRepository.saveAccount(account);
        operationHistoryRepository.addOperation(account, new Operation(WITHDRAW, LocalDate.now(), amount));
    }

    public void deposit(Account account, BigDecimal amount) {
        account.deposit(amount);
        accountRepository.saveAccount(account);
        operationHistoryRepository.addOperation(account, new Operation(DEPOSIT, LocalDate.now(), amount));
    }

    public List<Operation> consultOperationsHistory(Account account) {
        return operationHistoryRepository.getHistory(account);
    }

}