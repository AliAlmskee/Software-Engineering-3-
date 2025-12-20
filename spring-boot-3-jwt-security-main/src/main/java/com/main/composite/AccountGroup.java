package com.main.composite;


import java.util.ArrayList;
import java.util.List;

public class AccountGroup implements TaxableAccountComponent {

    private final List<TaxableAccountComponent> children = new ArrayList<>();

    public AccountGroup() {}

    public void add(TaxableAccountComponent component) {
        children.add(component);
    }

    @Override
    public double getBalance() {
        return children.stream().mapToDouble(TaxableAccountComponent::getBalance).sum();
    }

    @Override
    public double getTax() {
        return children.stream().mapToDouble(TaxableAccountComponent::getTax).sum();
    }
}