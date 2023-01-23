package com.driver.controller;

import com.driver.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")

public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping
    public ResponseEntity<String> issueBook(@RequestParam("cardId") int cardId,
                                    @RequestParam("bookId") int bookId) throws Exception{
        transactionService.issueBook(cardId,bookId);
       return new ResponseEntity<>("transaction completed", HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity returnBook(@RequestParam("cardId") int cardId,
                                     @RequestParam("bookId") int bookId) throws Exception{
        transactionService.returnBook(cardId,bookId);
        return new ResponseEntity<>("transaction completed", HttpStatus.ACCEPTED);
    }
}
