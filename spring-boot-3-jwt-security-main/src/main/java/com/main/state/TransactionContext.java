package com.main.state;

import lombok.Getter;

@Getter
public class TransactionContext {
    private TransactionState currentState;
    private String transactionId;
    private String message;
    
    public TransactionContext(String transactionId) {
        this.transactionId = transactionId;
        this.currentState = new PendingState();
        this.message = "Transaction created";
    }
    
    public void process() {
        this.currentState = currentState.process();
    }
    
    public void complete() {
        this.currentState = currentState.complete();
        this.message = "Transaction completed successfully";
    }
    
    public void fail() {
        this.currentState = currentState.fail();
        this.message = "Transaction failed";
    }
    
    public void cancel() {
        if (currentState.canCancel()) {
            this.currentState = currentState.cancel();
            this.message = "Transaction cancelled";
        } else {
            throw new IllegalStateException("Cannot cancel transaction in " + currentState.getStatusName() + " state");
        }
    }
    
    public void retry() {
        if (currentState.canRetry()) {
            this.currentState = new PendingState();
            this.message = "Transaction retried";
        } else {
            throw new IllegalStateException("Cannot retry transaction in " + currentState.getStatusName() + " state");
        }
    }
    
    public String getStatus() {
        return currentState.getStatusName();
    }
}

