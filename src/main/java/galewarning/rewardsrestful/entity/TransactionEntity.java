package galewarning.rewardsrestful.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "transaction_seq")
    // already have 19 transactions in the database, start from id 20
    @SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", allocationSize = 1, initialValue = 20)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "amount")
    private Double amount;

    public TransactionEntity() {
    }

    public TransactionEntity(Long id, Long customerId, Timestamp date, Double amount) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
