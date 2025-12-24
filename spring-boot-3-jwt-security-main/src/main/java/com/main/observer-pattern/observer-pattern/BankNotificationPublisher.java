package com.main.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankNotificationPublisher {
    
    private final List<BankNotificationListener> listeners = new ArrayList<>();
    
    @Autowired
    private EmailNotificationListener emailListener;
    
    @Autowired
    private SMSNotificationListener smsListener;
    
    public void subscribe(BankNotificationListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
            System.out.println(" New subscriber added: " + 
                (listener instanceof EmailNotificationListener ? "Email" : 
                 listener instanceof SMSNotificationListener ? "text messages" : "subscriber"));
        }
    }
    
    public void unsubscribe(BankNotificationListener listener) {
        listeners.remove(listener);
        System.out.println("subscriber removed");
    }
    
    public void publishEvent(BankEvent event) {
        System.out.println("\n The bank announces a new event:");
        System.out.println("event: " + event.getMessage());
        System.out.println("number of subscribers : " + listeners.size());
        
        if (listeners.isEmpty()) {
            System.out.println("  There are no notification subscribers!");
            return;
        }
        
        for (BankNotificationListener listener : listeners) {
            listener.onEvent(event);
        }
        
        System.out.println(" The notification has been sent to all subscribers\n");
    }
    
    public void autoSubscribeAll() {
        subscribe(emailListener);
        subscribe(smsListener);
        System.out.println(" All notification services are automatically subscribed");
    }
    
    public int getSubscriberCount() {
        return listeners.size();
    }
}
