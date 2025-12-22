package com.main.facade;

import com.main.adapter.*;
import com.main.entity.Account;
import com.main.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionFacade {

    private final AccountRepository accountRepository;

    public boolean transfer(Long senderId,
                            Long receiverId,
                            Integer amount,
                            String method) {

        Account sender = accountRepository.findById(senderId)
                .orElseThrow();

        Account receiver = accountRepository.findById(receiverId)
                .orElseThrow();

        PaymentProcessor processor = switch (method.toUpperCase()) {
            case "LEGACY" -> new LegacyBankAdapter();
            case "WIRE" -> new InternationalWireAdapter();
            default -> throw new IllegalArgumentException("Unknown method");
        };

        return processor.process(
                amount,
                sender.getId().toString(),
                receiver.getId().toString()
        );
    }
}
