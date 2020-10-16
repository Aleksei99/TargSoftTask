package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Unit test for App.
 */
public class AppTest {

    URL url = Thread.currentThread().getContextClassLoader().getResource("data.csv");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Test
    public void testDataCSV() throws FileNotFoundException {
        String filePath = url.getPath();
        List<Transaction> transactions = CsvResolver.getTransactions(filePath);
        Assert.assertEquals(new Report(1, new BigDecimal("59.99")),
                getSpecificReport(transactions));
    }

    private Report getSpecificReport(List<Transaction> transactions) {
        LocalDateTime fromDate = LocalDateTime.parse("20/08/2018 12:00:00", DATE_TIME_FORMATTER);
        LocalDateTime toDate = LocalDateTime.parse("20/08/2018 13:00:00", DATE_TIME_FORMATTER);
        String merchant = "Kwik-E-Mart";
        return Analyser.analyze(transactions, fromDate, toDate, merchant);
    }
}
