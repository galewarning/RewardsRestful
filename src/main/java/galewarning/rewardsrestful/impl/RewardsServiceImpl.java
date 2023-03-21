package galewarning.rewardsrestful.impl;

import galewarning.rewardsrestful.dao.TransactionRepository;
import galewarning.rewardsrestful.entity.TransactionEntity;
import galewarning.rewardsrestful.model.Rewards;
import galewarning.rewardsrestful.model.Transaction;
import galewarning.rewardsrestful.service.RewardsService;
import galewarning.rewardsrestful.util.TransactionEntityVoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service("rewardsService")
public class RewardsServiceImpl implements RewardsService {
    @Autowired
    private TransactionRepository transactionRepository;

    private static final long THRESHOLD1 = 50;
    private static final long THRESHOLD2 = 100;

    // get the current date and time
    private LocalDate now = LocalDate.now();

    // calculate the start and end of the last three months
    private Timestamp timestampStartOfMonth = Timestamp.valueOf(now.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay());
    private Timestamp timestampEndOfMonth = Timestamp.valueOf(now.with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay());
    private Timestamp timestampStartOfLastMonth = Timestamp.valueOf(now.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(1).atStartOfDay());
    private Timestamp timestampEndOfLastMonth = Timestamp.valueOf(now.with(TemporalAdjusters.lastDayOfMonth()).minusMonths(1).atStartOfDay());
    private Timestamp timestampStartOfMonthBeforeLastMonth = Timestamp.valueOf(now.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(2).atStartOfDay());
    private Timestamp timestampEndOfMonthBeforeLastMonth = Timestamp.valueOf(now.with(TemporalAdjusters.lastDayOfMonth()).minusMonths(2).atStartOfDay());

    @Override
    public Rewards getRewardsByCustomerId(Long customerId) {
        long firstMonthRewards = firstMonthRewards(customerId);
        long secondMonthRewards = secondMonthRewards(customerId);
        long thirdMonthRewards = thirdMonthRewards(customerId);
        long totalRewards = firstMonthRewards + secondMonthRewards + thirdMonthRewards;
        Rewards rewards = new Rewards(customerId,
                firstMonthRewards,
                secondMonthRewards,
                thirdMonthRewards,
                totalRewards);
        return rewards;
    }

    @Override
    public List<Transaction> firstMonthTransactions(Long customerId) {
        List<TransactionEntity> transactions = transactionRepository
                .findAllByCustomerIdAndDateBetween(customerId, timestampStartOfMonth, timestampEndOfMonth);
        return transactions.stream().map(e -> TransactionEntityVoConverter.convertEntityToVo(e)).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> secondMonthTransactions(Long customerId) {
        List<TransactionEntity> transactions = transactionRepository
                .findAllByCustomerIdAndDateBetween(customerId, timestampStartOfLastMonth, timestampEndOfLastMonth);
        return transactions.stream().map(e -> TransactionEntityVoConverter.convertEntityToVo(e)).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> thirdMonthTransactions(Long customerId) {
        List<TransactionEntity> transactions = transactionRepository
                .findAllByCustomerIdAndDateBetween(customerId, timestampStartOfMonthBeforeLastMonth, timestampEndOfMonthBeforeLastMonth);
        return transactions.stream().map(e -> TransactionEntityVoConverter.convertEntityToVo(e)).collect(Collectors.toList());
    }

    @Override
    public Long firstMonthRewards(Long customerId) {
        List<Transaction> transactions = firstMonthTransactions(customerId);
        return calculateRewards(transactions);
    }

    @Override
    public Long secondMonthRewards(Long customerId) {
        List<Transaction> transactions = secondMonthTransactions(customerId);
        return calculateRewards(transactions);
    }

    @Override
    public Long thirdMonthRewards(Long customerId) {
        List<Transaction> transactions = thirdMonthTransactions(customerId);
        return calculateRewards(transactions);
    }

    private long calculateRewards(List<Transaction> transactions) {
        long rewards = 0;
        for (Transaction transaction : transactions) {
            double currentAmount = transaction.getAmount();
            if (currentAmount <= 50) {
                continue;
            } else if (currentAmount <= 100) {
                rewards += currentAmount - THRESHOLD1;
            } else {
                rewards += currentAmount - THRESHOLD1;
                rewards += currentAmount - THRESHOLD2;
            }
        }
        return rewards;
    }
}
