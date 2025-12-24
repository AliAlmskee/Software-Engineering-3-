package com.main.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CompletedState Tests")
class CompletedStateTest {

    private TransactionState completedState;

    @BeforeEach
    void setUp() {
        completedState = new CompletedState();
    }

    @Test
    @DisplayName("Should throw exception when process is called")
    void shouldThrowExceptionWhenProcessIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> completedState.process());
        assertEquals("Transaction already completed", exception.getMessage());
    }

    @Test
    @DisplayName("Should return itself when complete is called")
    void shouldReturnItselfWhenCompleteIsCalled() {
        TransactionState newState = completedState.complete();
        assertSame(completedState, newState);
        assertInstanceOf(CompletedState.class, newState);
    }

    @Test
    @DisplayName("Should throw exception when fail is called")
    void shouldThrowExceptionWhenFailIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> completedState.fail());
        assertEquals("Cannot fail completed transaction", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when cancel is called")
    void shouldThrowExceptionWhenCancelIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> completedState.cancel());
        assertEquals("Cannot cancel completed transaction", exception.getMessage());
    }
}
