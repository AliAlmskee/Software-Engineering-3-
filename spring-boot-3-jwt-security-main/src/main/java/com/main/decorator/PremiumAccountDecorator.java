package com.main.decorator;

public class PremiumAccountDecorator extends AccountDecorator {

    public PremiumAccountDecorator(AccountComponent wrapped) {
        super(wrapped);
    }

    @Override
    public double getAmount() {
        return super.getAmount() * 1.10;
    }
}
