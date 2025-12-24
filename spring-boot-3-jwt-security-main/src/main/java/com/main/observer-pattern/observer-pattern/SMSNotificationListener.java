package com.main.observer;

import org.springframework.stereotype.Component;

@Component
public class SMSNotificationListener implements BankNotificationListener {
    
    private final String serviceName = "SMS_SERVICE";

    @Override
    public void onEvent(BankEvent event) {
        System.out.println("=".repeat(50));
        System.out.println(" Send Email");
        System.out.println("service: " + serviceName);
        System.out.println("to: +9665xxxxxxxx (client number)");
        System.out.println("message: " + event.getMessage());
        System.out.println("time: " + event.getTimestamp());
        System.out.println("=".repeat(50));
    }
    
    public String getServiceName() {
        return serviceName;
    }
}
