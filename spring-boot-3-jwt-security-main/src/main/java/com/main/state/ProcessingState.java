package com.main.state;

public class ProcessingState implements TransactionState {
    @Override
    public TransactionState process() {
        return this;
    }
    
    @Override
    public TransactionState complete() {
        return new CompletedState();
    }
    
    @Override
    public TransactionState fail() {
        return new FailedState();
    }
    
    @Override
    public TransactionState cancel() {
        throw new IllegalStateException("Cannot cancel processing transaction");
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
        return TransactionStatus.PROCESSING.name();
    }
}

