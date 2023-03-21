package galewarning.rewardsrestful.controller;

import galewarning.rewardsrestful.exception.TransactionNotFoundException;
import galewarning.rewardsrestful.model.ErrorResponse;
import galewarning.rewardsrestful.model.ResponseMessage;
import galewarning.rewardsrestful.model.Transaction;
import galewarning.rewardsrestful.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @GetMapping("/transactions")
    public ResponseEntity<?> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.valueOf(200));
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            throw new TransactionNotFoundException("transaction doesn't exist");
        }
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/transactions")
    public ResponseEntity<ResponseMessage> createTransaction(@Validated @RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(new ResponseMessage("transaction has been created", createdTransaction), HttpStatus.CREATED);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") long id, @Validated @RequestBody Transaction transaction) {
        Transaction currentTransaction = transactionService.findById(id);
        if (currentTransaction == null) {
            throw new TransactionNotFoundException("Transaction doesn't exist");
        }

        currentTransaction.setCustomerId(transaction.getCustomerId());
        currentTransaction.setDate(transaction.getDate());
        currentTransaction.setAmount(transaction.getAmount());
        transactionService.updateTransaction(currentTransaction);
        return new ResponseEntity<>(currentTransaction, HttpStatus.OK);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<ResponseMessage> deleteTransaction(@PathVariable("id") long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            throw new TransactionNotFoundException("transaction doesn't exist");
        }
        transactionService.deleteTransactionById(id);
        return new ResponseEntity<>(new ResponseMessage("transaction has been deleted", transaction), HttpStatus.OK);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerTransactionNotFound(Exception ex) {
        logger.error("Can't find transaction");
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
