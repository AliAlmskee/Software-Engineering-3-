package com.main.state;

public class CancelledState implements TransactionState {
    @Override
    public TransactionState process() {
        throw new IllegalStateException("Cannot process cancelled transaction");
    }
    
    @Override
    public TransactionState complete() {
        throw new IllegalStateException("Cannot complete cancelled transaction");
    }
    
    @Override
    public TransactionState fail() {
        throw new IllegalStateException("Cannot fail cancelled transaction");
    }
    
    @Override
    public TransactionState cancel() {
        return this;
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
        return TransactionStatus.CANCELLED.name();
    }
}

