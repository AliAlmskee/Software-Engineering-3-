package com.main.adapter;

public class LegacyBankAdapter implements PaymentProcessor {

    private final LegacyBankApi bankApi = new LegacyBankApi();

    @Override
    public boolean process(Integer amount, String sender, String receiver) {
        return bankApi.executeTransfer(amount, sender, receiver);
    }
}