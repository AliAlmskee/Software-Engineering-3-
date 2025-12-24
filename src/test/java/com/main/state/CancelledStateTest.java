package com.main.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CancelledState Tests")
class CancelledStateTest {

    private TransactionState cancelledState;

    @BeforeEach
    void setUp() {
        cancelledState = new CancelledState();
    }

    @Test
    @DisplayName("Should throw exception when process is called")
    void shouldThrowExceptionWhenProcessIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> cancelledState.process());
        assertEquals("Cannot process cancelled transaction", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when complete is called")
    void shouldThrowExceptionWhenCompleteIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> cancelledState.complete());
        assertEquals("Cannot complete cancelled transaction", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when fail is called")
    void shouldThrowExceptionWhenFailIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> cancelledState.fail());
        assertEquals("Cannot fail cancelled transaction", exception.getMessage());
    }

    @Test
    @DisplayName("Should return itself when cancel is called")
    void shouldReturnItselfWhenCancelIsCalled() {
        TransactionState newState = cancelledState.cancel();
        assertSame(cancelledState, newState);
        assertInstanceOf(CancelledState.class, newState);
    }
}
