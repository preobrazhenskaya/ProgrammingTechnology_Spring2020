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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "client_id")
    private Long clientId;
    @Column
    private BigDecimal amount;
    @Column(name = "acc_code")
    private String accCode;

    public Account(Long clientId, BigDecimal amount, String accCode) {
        this.clientId = clientId;
        this.amount = amount;
        this.accCode = accCode;
    }
}
