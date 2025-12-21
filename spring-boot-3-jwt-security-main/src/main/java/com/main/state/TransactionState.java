package com.main.state;

public interface TransactionState {
    TransactionState process();
    TransactionState complete();
    TransactionState fail();
    TransactionState cancel();
    boolean canCancel();
    boolean canRetry();
    String getStatusName();
}

