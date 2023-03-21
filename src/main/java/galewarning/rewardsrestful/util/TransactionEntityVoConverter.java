package galewarning.rewardsrestful.util;


import galewarning.rewardsrestful.entity.TransactionEntity;
import galewarning.rewardsrestful.model.Transaction;

public class TransactionEntityVoConverter {
    public static Transaction convertEntityToVo(TransactionEntity entity) {
        if (entity == null) return null;

        Transaction transaction = new Transaction();
        transaction.setId(entity.getId());
        transaction.setCustomerId(entity.getCustomerId());
        transaction.setDate(entity.getDate());
        transaction.setAmount(entity.getAmount());
        return transaction;
    }

    public static TransactionEntity convertVoToEntity(Transaction transaction) {
        if (transaction == null) return null;

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transaction.getId());
        transactionEntity.setCustomerId(transaction.getCustomerId());
        transactionEntity.setDate(transaction.getDate());
        transactionEntity.setAmount(transaction.getAmount());
        return transactionEntity;
    }
}
