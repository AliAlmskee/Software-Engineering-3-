package com.main.adapter;

public class InternationalWireAdapter implements PaymentProcessor {

    private final InternationalWireApi wireApi = new InternationalWireApi();

    @Override
    public boolean process(Integer amount, String sender, String receiver) {
        wireApi.sendWire(sender, receiver, amount);
        return true;
    }
}