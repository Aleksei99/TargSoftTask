package org.example;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Report {
    private int numberOfTransactions;
    private BigDecimal averageTransactionValue;
}
