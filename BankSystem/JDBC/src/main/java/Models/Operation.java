package Models;

import java.math.BigDecimal;
import java.util.UUID;

public class Operation {
    private UUID id;
    private String date;
    private CurrencyCode currency;
    private UUID fromAccount;
    private UUID toAccount;
    private BigDecimal amount;
    private BigDecimal amountBefore;
    private BigDecimal amountAfter;

    public Operation() { }

    public Operation(UUID id, String date, CurrencyCode currency, UUID fromAccount, UUID toAccount, BigDecimal amount, BigDecimal amountBefore, BigDecimal amountAfter) {
        this.id = id;
        this.date = date;
        this.currency = currency;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CurrencyCode getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyCode currency) {
        this.currency = currency;
    }

    public UUID getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(UUID fromAccount) {
        this.fromAccount = fromAccount;
    }

    public UUID getToAccount() {
        return toAccount;
    }

    public void setToAccount(UUID toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountBefore() {
        return amountBefore;
    }

    public void setAmountBefore(BigDecimal amountBefore) {
        this.amountBefore = amountBefore;
    }

    public BigDecimal getAmountAfter() {
        return amountAfter;
    }

    public void setAmountAfter(BigDecimal amountAfter) {
        this.amountAfter = amountAfter;
    }
}
