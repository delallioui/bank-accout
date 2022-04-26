package sg.bank.account.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OperationTest {
    @Test
    void should_not_be_able_to_create_invalid_operation() {
        assertThrows(Exception.class, () -> new Operation(null, null, null));
    }
}