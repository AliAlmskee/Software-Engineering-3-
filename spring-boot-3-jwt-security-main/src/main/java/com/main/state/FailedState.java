package com.main.state;

public class FailedState implements TransactionState {
    @Override
    public TransactionState process() {
        throw new IllegalStateException("Cannot process failed transaction");
    }
    
    @Override
    public TransactionState complete() {
        throw new IllegalStateException("Cannot complete failed transaction");
    }
    
    @Override
    public TransactionState fail() {
        return this;
    }
    
    @Override
    public TransactionState cancel() {
        throw new IllegalStateException("Cannot cancel failed transaction");
    }
    
    @Override
    public boolean canCancel() {
        return false;
    }
    
    @Override
    public boolean canRetry() {
        return true;
    }
    
    @Override
    public String getStatusName() {
        return TransactionStatus.FAILED.name();
    }
}

