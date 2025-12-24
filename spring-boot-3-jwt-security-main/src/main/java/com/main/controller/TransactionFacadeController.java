package com.main.controller;

import com.main.services.TransactionFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction/v2")
@RequiredArgsConstructor
public class TransactionFacadeController {

    private final TransactionFacadeService service;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam Integer amount,
            @RequestParam String method
    ) {

        boolean result = service.transfer(
                senderId,
                receiverId,
                amount,
                method
        );

        return ResponseEntity.ok(
                result ? "Transfer complete" : "Transfer failed"
        );
    }
}

