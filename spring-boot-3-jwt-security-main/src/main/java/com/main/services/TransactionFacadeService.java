package com.main.services;

import com.main.facade.TransactionFacade;
import com.main.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionFacadeService {

    private final TransactionFacade facade;

    public boolean transfer(Long senderId,
                            Long receiverId,
                            Integer amount,
                            String method) {
        return facade.transfer(senderId, receiverId, amount, method);
    }
}

