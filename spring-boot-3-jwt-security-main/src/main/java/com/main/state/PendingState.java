package com.main.state;

public class PendingState implements TransactionState {
    @Override
    public TransactionState process() {
        return new ProcessingState();
    }
    
    @Override
    public TransactionState complete() {
        throw new IllegalStateException("Cannot complete pending transaction");
    }
    
    @Override
    public TransactionState fail() {
        throw new IllegalStateException("Cannot fail pending transaction");
    }
    
    @Override
    public TransactionState cancel() {
        return new CancelledState();
    }
    
    @Override
    public boolean canCancel() {
        return true;
    }
    
    @Override
    public boolean canRetry() {
        return false;
    }
    
    @Override
    public String getStatusName() {
        return TransactionStatus.PENDING.name();
    }
}

