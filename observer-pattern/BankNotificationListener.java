package com.main.observer;

public interface BankNotificationListener {
    void onEvent(BankEvent event);
}
