package galewarning.rewardsrestful.impl;

import galewarning.rewardsrestful.dao.CustomerRepository;
import galewarning.rewardsrestful.dao.TransactionRepository;
import galewarning.rewardsrestful.entity.CustomerEntity;
import galewarning.rewardsrestful.entity.TransactionEntity;
import galewarning.rewardsrestful.model.Transaction;
import galewarning.rewardsrestful.service.TransactionService;
import galewarning.rewardsrestful.util.CustomerEntityVoConverter;
import galewarning.rewardsrestful.util.TransactionEntityVoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAllTransactions() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        return transactions.stream().map(e -> TransactionEntityVoConverter.convertEntityToVo(e)).collect(Collectors.toList());
    }

    @Override
    public Transaction findById(long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).orElse(null);
        return TransactionEntityVoConverter.convertEntityToVo(transactionEntity);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = transactionRepository.saveAndFlush(TransactionEntityVoConverter
                .convertVoToEntity(transaction));
        return TransactionEntityVoConverter.convertEntityToVo(transactionEntity);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = transactionRepository.saveAndFlush(TransactionEntityVoConverter
                .convertVoToEntity(transaction));
        return TransactionEntityVoConverter.convertEntityToVo(transactionEntity);
    }

    @Override
    public void deleteTransactionById(long id) {
        transactionRepository.deleteById(id);
    }
}
