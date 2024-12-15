package org.omni.bank.transaction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @PostMapping(value = "/")
    public void createTransaction() {
//        TODO
    }

    @GetMapping(value = "/{transactionId}")
    public void findTransactionByTransactionId() {
//        TODO
    }

    @GetMapping(value = "/accounts/{accountId}")
    public void findTransactionsByAccountId() {
//        TODO
//        put request param to filter with start data, end date, transaction type
    }

    @GetMapping(value = "/accounts/{accNumber}")
    public void findTransactionsByAccountNumber() {
//        TODO
//        put request param to filter with start data, end date, transaction type
    }

    @GetMapping(value = "/customers/{customerId}")
    public void findTransactionsByCustomerId() {
//        TODO
//        put request param to filter with start data, end date, transaction type
    }

    @GetMapping(value = "/summary")
    public void findTransactionSummary() {
//        TODO
//        only for admin use
    }

    @GetMapping(value = "/summary/accounts/{accountId}")
    public void findTransactionSummaryByAccountId() {
//        TODO
    }

    @GetMapping(value = "/summary/customers/{customerId}")
    public void findTransactionSummaryByCustomerId() {
//        TODO
    }

}
