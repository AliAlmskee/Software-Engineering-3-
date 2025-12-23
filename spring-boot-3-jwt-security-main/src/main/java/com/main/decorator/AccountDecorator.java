package com.main.decorator;

public abstract class AccountDecorator implements AccountComponent {

    protected final AccountComponent wrapped;

    protected AccountDecorator(AccountComponent wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public double getAmount() {
        return wrapped.getAmount();
    }
}
