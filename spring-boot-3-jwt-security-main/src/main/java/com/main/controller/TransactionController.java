package com.main.controller;

import com.main.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestParam Long senderId,
                                      @RequestParam Long receiverId,
                                      @RequestParam Integer amount,
                                      @RequestParam String method) {

        boolean success = transactionService.transfer(senderId, receiverId, amount, method);

        return ResponseEntity.ok(success ? "Transfer complete" : "Transfer failed");
    }


}
