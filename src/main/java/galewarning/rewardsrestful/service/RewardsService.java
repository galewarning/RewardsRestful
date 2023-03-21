package galewarning.rewardsrestful.service;

import galewarning.rewardsrestful.model.Rewards;
import galewarning.rewardsrestful.model.Transaction;

import java.util.List;

public interface RewardsService {
    Rewards getRewardsByCustomerId(Long customerId);
    List<Transaction> firstMonthTransactions(Long customerId);
    List<Transaction> secondMonthTransactions(Long customerId);
    List<Transaction> thirdMonthTransactions(Long customerId);
    Long firstMonthRewards(Long customerId);
    Long secondMonthRewards(Long customerId);
    Long thirdMonthRewards(Long customerId);
}
