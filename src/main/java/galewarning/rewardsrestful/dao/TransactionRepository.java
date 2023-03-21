package galewarning.rewardsrestful.dao;

import galewarning.rewardsrestful.entity.TransactionEntity;
import galewarning.rewardsrestful.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByCustomerIdAndDateBetween(Long customerId, Timestamp timestampFrom, Timestamp timestampTo);
}
