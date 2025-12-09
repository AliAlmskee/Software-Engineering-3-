package com.main.adapter;

public class LegacyBankApi {
    public boolean executeTransfer(Integer amount, String from, String to) {
        System.out.println("LegacyBank transfer executed");
        return true;
    }
}