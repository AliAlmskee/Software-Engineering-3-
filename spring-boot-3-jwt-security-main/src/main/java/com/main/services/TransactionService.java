package com.main.services;

import com.main.adapter.InternationalWireAdapter;
import com.main.adapter.LegacyBankAdapter;
import com.main.adapter.PaymentProcessor;
import com.main.entity.Account;
import com.main.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;

    public boolean transfer(Long senderId,
                            Long receiverId,
                            Integer amount,
                            String method) {

        Account sender = accountRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

        Account receiver = accountRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        PaymentProcessor processor;

        switch (method.toUpperCase()) {
            case "LEGACY":
                processor = new LegacyBankAdapter();
                break;

            case "WIRE":
                processor = new InternationalWireAdapter();
                break;

            default:
                throw new IllegalArgumentException("Unknown payment method");
        }

        return executeTransfer(sender, receiver, amount, processor);
    }

    public boolean executeTransfer(Account sender,
                                   Account receiver,
                                   Integer amount,
                                   PaymentProcessor processor) {

        return processor.process(
                amount,
                sender.getId().toString(),
                receiver.getId().toString()
        );
    }
}
