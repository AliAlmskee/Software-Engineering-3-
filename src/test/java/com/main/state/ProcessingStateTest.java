package com.main.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProcessingState Tests")
class ProcessingStateTest {

    private TransactionState processingState;

    @BeforeEach
    void setUp() {
        processingState = new ProcessingState();
    }

    @Test
    @DisplayName("Should return itself when process is called")
    void shouldReturnItselfWhenProcessIsCalled() {
        TransactionState newState = processingState.process();
        assertSame(processingState, newState);
        assertInstanceOf(ProcessingState.class, newState);
    }

    @Test
    @DisplayName("Should transition to CompletedState when complete is called")
    void shouldTransitionToCompletedStateWhenCompleteIsCalled() {
        TransactionState newState = processingState.complete();
        assertInstanceOf(CompletedState.class, newState);
    }

    @Test
    @DisplayName("Should transition to FailedState when fail is called")
    void shouldTransitionToFailedStateWhenFailIsCalled() {
        TransactionState newState = processingState.fail();
        assertInstanceOf(FailedState.class, newState);
    }

    @Test
    @DisplayName("Should throw exception when cancel is called")
    void shouldThrowExceptionWhenCancelIsCalled() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> processingState.cancel());
        assertEquals("Cannot cancel processing transaction", exception.getMessage());
    }
}
