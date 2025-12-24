package com.main.decorator;

public class OverdraftAccountDecorator extends AccountDecorator {

    private static final double OVERDRAFT_LIMIT = 500;

    public OverdraftAccountDecorator(AccountComponent wrapped) {
        super(wrapped);
    }

    @Override
    public double getAmount() {
        return super.getAmount() + OVERDRAFT_LIMIT;
    }
}
