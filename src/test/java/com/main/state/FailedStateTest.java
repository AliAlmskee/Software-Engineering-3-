package com.main.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FailedState Tests")
class FailedStateTest {

    private TransactionState failedState;

    @BeforeEach
    void setUp() {
        failedState = new FailedState();
    }

    @Test
    @DisplayName("Should throw exception when process is called")
    void shouldThrowExceptionWhenProcessIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> failedState.process());
        assertEquals("Cannot process failed transaction", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when complete is called")
    void shouldThrowExceptionWhenCompleteIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> failedState.complete());
        assertEquals("Cannot complete failed transaction", exception.getMessage());
    }

    @Test
    @DisplayName("Should return itself when fail is called")
    void shouldReturnItselfWhenFailIsCalled() {
        TransactionState newState = failedState.fail();
        assertSame(failedState, newState);
        assertInstanceOf(FailedState.class, newState);
    }

    @Test
    @DisplayName("Should throw exception when cancel is called")
    void shouldThrowExceptionWhenCancelIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> failedState.cancel());
        assertEquals("Cannot cancel failed transaction", exception.getMessage());
    }
}
```

```

