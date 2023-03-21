package galewarning.rewardsrestful.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class Transaction {
    private Long id;
    private Long customerId;
    private Timestamp date;
    @Min(0)
    private Double amount;

    public Transaction() {
    }

    public Transaction(Long id, Long customerId, Timestamp date, Double amount) {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
