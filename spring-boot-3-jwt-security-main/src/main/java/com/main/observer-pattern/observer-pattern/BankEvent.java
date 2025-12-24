package com.main.observer;

import java.time.LocalDateTime;

public class BankEvent {
    private String eventType;
    private String accountId;
    private String customerId;
    private double amount;
    private LocalDateTime timestamp;
    private String message;

    public BankEvent(String eventType, String accountId, String customerId, 
                     double amount, String message) {
        this.eventType = eventType;
        this.accountId = accountId;
        this.customerId = customerId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public String getEventType() { return eventType; }
    public String getAccountId() { return accountId; }
    public String getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getMessage() { return message; }

    @Override
    public String toString() {
        return "BankEvent{" +
                "eventType='" + eventType + '\'' +
                ", accountId='" + accountId + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
