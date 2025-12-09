package com.main.adapter;

public interface PaymentProcessor {
    boolean process(Integer amount, String senderAccountNumber, String receiverAccountNumber);
}