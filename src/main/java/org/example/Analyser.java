package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Analyser {
    public static Report analyze(List<Transaction> transactions,
                                 LocalDateTime fromDate, LocalDateTime toDate,
                                 String merchant) {
        int numberOfTransactions;
        BigDecimal averageTransactionValue = null;
        List<Transaction> merchantTransactionList = new ArrayList<>();
        List<Transaction> reversTransactionList = new ArrayList<>();
        List<Transaction> resultTransactionList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (fromDate.compareTo(transaction.getDate()) <= 0 &&
                    toDate.compareTo(transaction.getDate()) >= 0 &&
                    transaction.getMerchant().equals(merchant)) {
                merchantTransactionList.add(transaction);
            }
            if (transaction.getType().equals("REVERSAL") &&
                    transaction.getMerchant().equals(merchant)) {
                reversTransactionList.add(transaction);
            }
        }

        for (Transaction transaction : merchantTransactionList) {
            boolean hasReversal = false;
            for (Transaction reversal : reversTransactionList) {
                if (transaction.getId().equals(reversal.getRelatedTransaction())) {
                    hasReversal = true;
                    break;
                }
            }
            if (!hasReversal)
                resultTransactionList.add(transaction);
        }

        numberOfTransactions = resultTransactionList.size();

        if (numberOfTransactions != 0) {
            BigDecimal sum = BigDecimal.valueOf(0);
            for (Transaction transaction : resultTransactionList) {
                sum = sum.add(transaction.getAmount());
            }
            averageTransactionValue = sum.divide(BigDecimal.valueOf(numberOfTransactions),
                    2, BigDecimal.ROUND_CEILING);
        }
        return new Report(numberOfTransactions, averageTransactionValue);
    }
}
