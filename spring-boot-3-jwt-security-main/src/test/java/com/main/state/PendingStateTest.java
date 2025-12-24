package com.main.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PendingState Tests")
class PendingStateTest {

    private TransactionState pendingState;

    @BeforeEach
    void setUp() {
        pendingState = new PendingState();
    }

    @Test
    @DisplayName("Should transition to ProcessingState when process is called")
    void shouldTransitionToProcessingStateWhenProcessIsCalled() {
        TransactionState newState = pendingState.process();
        assertInstanceOf(ProcessingState.class, newState);
    }

    @Test
    @DisplayName("Should throw exception when complete is called")
    void shouldThrowExceptionWhenCompleteIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> pendingState.complete());
        assertEquals("Cannot complete pending transaction", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when fail is called")
    void shouldThrowExceptionWhenFailIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> pendingState.fail());
        assertEquals("Cannot fail pending transaction", exception.getMessage());
    }

    @Test
    @DisplayName("Should transition to CancelledState when cancel is called")
    void shouldTransitionToCancelledStateWhenCancelIsCalled() {
        TransactionState newState = pendingState.cancel();
        assertInstanceOf(CancelledState.class, newState);
    }
}
