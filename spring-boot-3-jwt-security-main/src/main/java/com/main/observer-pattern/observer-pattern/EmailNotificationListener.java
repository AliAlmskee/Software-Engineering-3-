package com.main.observer;

import org.springframework.stereotype.Component;

@Component
public class EmailNotificationListener implements BankNotificationListener {
    
    private final String serviceName = "EMAIL_SERVICE";

    @Override
    public void onEvent(BankEvent event) {
        System.out.println("=".repeat(50));
        System.out.println("Send Email");
        System.out.println("service" + serviceName);
        System.out.println("to: customer-" + event.getCustomerId() + "@bank.com");
        System.out.println("Subject: Bank notice - " + event.getEventType());
        System.out.println("content: " + event.getMessage());
        System.out.println("time: " + event.getTimestamp());
        System.out.println("=".repeat(50));
    }
    
    public String getServiceName() {
        return serviceName;
    }
}
