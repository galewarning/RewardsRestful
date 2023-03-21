package galewarning.rewardsrestful.service;

import galewarning.rewardsrestful.model.Customer;
import galewarning.rewardsrestful.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAllTransactions();
    Transaction findById(long id);
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);
    void deleteTransactionById(long id);
}
