package galewarning.rewardsrestful.impl;

import galewarning.rewardsrestful.dao.TransactionRepository;
import galewarning.rewardsrestful.entity.TransactionEntity;
import galewarning.rewardsrestful.model.Rewards;
import galewarning.rewardsrestful.model.Transaction;
import galewarning.rewardsrestful.util.TransactionEntityVoConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RewardsServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private RewardsServiceImpl rewardsService;

    private LocalDate now = LocalDate.now();
    private Timestamp timestampStartOfMonth = Timestamp.valueOf(now.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay());
    private Timestamp timestampEndOfMonth = Timestamp.valueOf(now.with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay());
    private Timestamp timestampStartOfLastMonth = Timestamp.valueOf(now.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(1).atStartOfDay());
    private Timestamp timestampEndOfLastMonth = Timestamp.valueOf(now.with(TemporalAdjusters.lastDayOfMonth()).minusMonths(1).atStartOfDay());
    private Timestamp timestampStartOfMonthBeforeLastMonth = Timestamp.valueOf(now.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(2).atStartOfDay());
    private Timestamp timestampEndOfMonthBeforeLastMonth = Timestamp.valueOf(now.with(TemporalAdjusters.lastDayOfMonth()).minusMonths(2).atStartOfDay());

    @Test
    void getRewardsByCustomerIdTest() {
        Rewards targetRewards = new Rewards();
        targetRewards.setCustomerId(1L);
        targetRewards.setFirstMonthRewardPoints(100L);
        targetRewards.setSecondMonthRewardPoints(200L);
        targetRewards.setThirdMonthRewardPoints(300L);
        targetRewards.setTotalRewardPoints(600L);

        mockFirstMonthTransaction();
        mockSecondMonthTransaction();
        mockThirdMonthTransaction();

        Assertions.assertEquals(targetRewards, rewardsService.getRewardsByCustomerId(1L));
    }

    private void mockFirstMonthTransaction() {
        TransactionEntity transactionThirdMonth = new TransactionEntity(1L,
                1L,
                timestampStartOfMonthBeforeLastMonth,
                225D);
        List<TransactionEntity> list = new ArrayList<>();
        list.add(transactionThirdMonth);
        Mockito.when(transactionRepository
                        .findAllByCustomerIdAndDateBetween(1L,
                                timestampStartOfMonthBeforeLastMonth,
                                timestampEndOfMonthBeforeLastMonth))
                        .thenReturn(list);
    }

    private void mockSecondMonthTransaction() {
        TransactionEntity transactionThirdMonth = new TransactionEntity(1L,
                1L,
                timestampStartOfLastMonth,
                175D);
        List<TransactionEntity> list = new ArrayList<>();
        list.add(transactionThirdMonth);
        Mockito.when(transactionRepository
                        .findAllByCustomerIdAndDateBetween(1L,
                                timestampStartOfLastMonth,
                                timestampEndOfLastMonth))
                .thenReturn(list);
    }

    private void mockThirdMonthTransaction() {
        TransactionEntity transactionThirdMonth = new TransactionEntity(1L,
                1L,
                timestampStartOfMonth,
                125D);
        List<TransactionEntity> list = new ArrayList<>();
        list.add(transactionThirdMonth);
        Mockito.when(transactionRepository
                        .findAllByCustomerIdAndDateBetween(1L,
                                timestampStartOfMonth,
                                timestampEndOfMonth))
                .thenReturn(list);
    }
}
