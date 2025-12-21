package com.main.state;

public class CompletedState implements TransactionState {
    @Override
    public TransactionState process() {
        throw new IllegalStateException("Transaction already completed");
    }
    
    @Override
    public TransactionState complete() {
        return this;
    }
    
    @Override
    public TransactionState fail() {
        throw new IllegalStateException("Cannot fail completed transaction");
    }
    
    @Override
    public TransactionState cancel() {
        throw new IllegalStateException("Cannot cancel completed transaction");
    }
    
    @Override
    public boolean canCancel() {
        return false;
    }
    
    @Override
    public boolean canRetry() {
        return false;
    }
    
    @Override
    public String getStatusName() {
        return TransactionStatus.COMPLETED.name();
    }
}

