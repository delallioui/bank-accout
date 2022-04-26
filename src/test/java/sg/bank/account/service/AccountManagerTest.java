package sg.bank.account.service;

import org.junit.jupiter.api.Test;
import sg.bank.account.model.Account;
import sg.bank.account.model.Operation;
import sg.bank.account.repository.AccountRepository;
import sg.bank.account.repository.OperationHistoryRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.time.LocalDate.now;
import static org.fest.assertions.Assertions.assertThat;
import static sg.bank.account.model.OperationType.DEPOSIT;
import static sg.bank.account.model.OperationType.WITHDRAW;

class AccountManagerTest {
    @Test
    void withDraw() {
        // given
        AccountRepository accountRepository = getAccountRepository(BigDecimal.TEN);
        Account account = accountRepository.getAccount();
        AccountManager accountManager = new AccountManager(accountRepository, getOperationHistoryRepository());
        //when
        accountManager.withDraw(account, ONE);
        //then
        Account accountFinal = accountRepository.getAccount();
        assertThat(accountFinal).isNotNull();
        assertThat(accountFinal.getBalance()).isEqualByComparingTo(new BigDecimal("9"));
    }


    @Test
    void deposit() {
        // given
        AccountRepository accountRepository = getAccountRepository(BigDecimal.TEN);
        Account account = accountRepository.getAccount();
        AccountManager accountManager = new AccountManager(accountRepository, getOperationHistoryRepository());
        //when
        accountManager.deposit(account, ONE);
        //then
        Account accountFinal = accountRepository.getAccount();
        assertThat(accountFinal).isNotNull();
        assertThat(accountFinal.getBalance()).isEqualByComparingTo(new BigDecimal("11"));
    }

    @Test
    void consultOperationsHistory() {
        // given
        AccountRepository accountRepository = getAccountRepository(BigDecimal.TEN);
        Account account = accountRepository.getAccount();
        OperationHistoryRepository operationHistoryRepository = getOperationHistoryRepository();
        AccountManager accountManager = new AccountManager(accountRepository, operationHistoryRepository);

        // when
        accountManager.deposit(account, ONE);
        accountManager.withDraw(account, new BigDecimal("2"));
        accountManager.deposit(account, new BigDecimal("3"));

        // then
        List<Operation> history = accountManager.consultOperationsHistory(account);
        assertThat(history).isNotNull();
        assertThat(history.size()).isEqualTo(3);
        assertThat(history.get(0)).isEqualTo(new Operation(DEPOSIT, now(), ONE));
        assertThat(history.get(1)).isEqualTo(new Operation(WITHDRAW, now(), new BigDecimal("2")));
        assertThat(history.get(2)).isEqualTo(new Operation(DEPOSIT, now(), new BigDecimal("3")));
    }


    private AccountRepository getAccountRepository(BigDecimal initialDefaultValue) {
        return new AccountRepository() {
            private Account account = new Account(initialDefaultValue);

            @Override
            public Account getAccount() {
                return this.account;
            }

            @Override
            public void saveAccount(Account account) {
                this.account = account;
            }
        };
    }

    private OperationHistoryRepository getOperationHistoryRepository() {
        return new OperationHistoryRepository() {
            private final List<Operation> operationHistory = new ArrayList<>();

            @Override
            public List<Operation> getHistory(Account account) {
                return this.operationHistory;
            }

            @Override
            public void addOperation(Account account, Operation operation) {
                this.operationHistory.add(operation);
            }
        };
    }
}