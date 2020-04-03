package ssu.BankSystemSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String date;
    @Column
    private String currency;
    @Column(name = "from_account")
    private Long fromAccount;
    @Column(name = "to_account")
    private Long toAccount;
    @Column
    private BigDecimal amount;
    @Column(name = "amount_before")
    private BigDecimal amountBefore;
    @Column(name = "amount_after")
    private BigDecimal amountAfter;

    public Operation(String date, String currency, Long fromAccount, Long toAccount, BigDecimal amount, BigDecimal amountBefore, BigDecimal amountAfter) {
        this.date = date;
        this.currency = currency;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
    }
}
