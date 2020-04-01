package Models;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private UUID id;
    private UUID clientId;
    private BigDecimal amount;
    private AccCode accCode;

    public Account () { }

    public Account (UUID id, UUID clientId, BigDecimal amount, AccCode accCode) {
        this.id = id;
        this.clientId = clientId;
        this.amount = amount;
        this.accCode = accCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AccCode getAccCode() {
        return accCode;
    }

    public void setAccCode(AccCode accCode) {
        this.accCode = accCode;
    }
}
