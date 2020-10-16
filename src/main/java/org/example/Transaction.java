package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Transaction {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private String id;
    private LocalDateTime date;
    private BigDecimal amount;
    private String merchant;
    private String type;
    private String relatedTransaction;

    public Transaction(TransactionCsv transactionCsv) {
        this.id = transactionCsv.getId();
        this.date = LocalDateTime.parse(transactionCsv.getDate(), DATE_TIME_FORMATTER);
        this.amount = new BigDecimal(transactionCsv.getAmount());
        this.merchant = transactionCsv.getMerchant();
        this.type = transactionCsv.getType();
        if (transactionCsv.getRelatedTransaction().equals("")) {
            this.relatedTransaction = null;
        } else this.relatedTransaction = transactionCsv.getRelatedTransaction();
    }
}
