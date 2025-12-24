package com.main.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TransactionContext Tests")
class TransactionContextTest {

    private TransactionContext context;

    @BeforeEach
    void setUp() {
        context = new TransactionContext("TXN-001");
    }

    @Test
    @DisplayName("Should initialize with PENDING state")
    void shouldInitializeWithPendingState() {
        assertEquals("PENDING", context.getStatus());
        assertEquals("Transaction created", context.getMessage());
        assertEquals("TXN-001", context.getTransactionId());
    }

    @Test
    @DisplayName("Should transition from PENDING to PROCESSING")
    void shouldTransitionFromPendingToProcessing() {
        context.process();
        assertEquals("PROCESSING", context.getStatus());
    }

    @Test
    @DisplayName("Should transition from PROCESSING to COMPLETED")
    void shouldTransitionFromProcessingToCompleted() {
        context.process();
        context.complete();
        assertEquals("COMPLETED", context.getStatus());
        assertEquals("Transaction completed successfully", context.getMessage());
    }

    @Test
    @DisplayName("Should transition from PROCESSING to FAILED")
    void shouldTransitionFromProcessingToFailed() {
        context.process();
        context.fail();
        assertEquals("FAILED", context.getStatus());
        assertEquals("Transaction failed", context.getMessage());
    }

    @Test
    @DisplayName("Should transition from PENDING to CANCELLED")
    void shouldTransitionFromPendingToCancelled() {
        context.cancel();
        assertEquals("CANCELLED", context.getStatus());
        assertEquals("Transaction cancelled", context.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when cancelling PROCESSING transaction")
    void shouldThrowExceptionWhenCancellingProcessingTransaction() {
        context.process();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> context.cancel());
        assertEquals("Cannot cancel transaction in PROCESSING state", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when cancelling COMPLETED transaction")
    void shouldThrowExceptionWhenCancellingCompletedTransaction() {
        context.process();
        context.complete();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> context.cancel());
        assertEquals("Cannot cancel transaction in COMPLETED state", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when cancelling FAILED transaction")
    void shouldThrowExceptionWhenCancellingFailedTransaction() {
        context.process();
        context.fail();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> context.cancel());
        assertEquals("Cannot cancel transaction in FAILED state", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when cancelling CANCELLED transaction")
    void shouldThrowExceptionWhenCancellingCancelledTransaction() {
        context.cancel();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> context.cancel());
        assertEquals("Cannot cancel transaction in CANCELLED state", exception.getMessage());
    }

    @Test
    @DisplayName("Should retry FAILED transaction")
    void shouldRetryFailedTransaction() {
        context.process();
        context.fail();
        context.retry();
        assertEquals("PENDING", context.getStatus());
        assertEquals("Transaction retried", context.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when retrying PENDING transaction")
    void shouldThrowExceptionWhenRetryingPendingTransaction() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> context.retry());
        assertEquals("Cannot retry transaction in PENDING state", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when retrying PROCESSING transaction")
    void shouldThrowExceptionWhenRetryingProcessingTransaction() {
        context.process();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> context.retry());
        assertEquals("Cannot retry transaction in PROCESSING state", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when retrying COMPLETED transaction")
    void shouldThrowExceptionWhenRetryingCompletedTransaction() {
        context.process();
        context.complete();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> context.retry());
        assertEquals("Cannot retry transaction in COMPLETED state", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when retrying CANCELLED transaction")
    void shouldThrowExceptionWhenRetryingCancelledTransaction() {
        context.cancel();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> context.retry());
        assertEquals("Cannot retry transaction in CANCELLED state", exception.getMessage());
    }

    @Test
    @DisplayName("Should handle complete transaction flow")
    void shouldHandleCompleteTransactionFlow() {
        assertEquals("PENDING", context.getStatus());
        context.process();
        assertEquals("PROCESSING", context.getStatus());
        context.complete();
        assertEquals("COMPLETED", context.getStatus());
    }

    @Test
    @DisplayName("Should handle failed transaction flow")
    void shouldHandleFailedTransactionFlow() {
        assertEquals("PENDING", context.getStatus());
        context.process();
        assertEquals("PROCESSING", context.getStatus());
        context.fail();
        assertEquals("FAILED", context.getStatus());
        context.retry();
        assertEquals("PENDING", context.getStatus());
    }
}
```

