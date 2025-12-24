package com.main.services;

import com.main.facade.TransactionFacade;
import com.main.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionFacadeService {

    private final TransactionFacade facade;

    public TransactionFacadeService(AccountRepository repository) {
        this.facade = new TransactionFacade(repository);
    }

    public boolean transfer(Long senderId,
                            Long receiverId,
                            Integer amount,
                            String method) {
        return facade.transfer(senderId, receiverId, amount, method);
    }
}

