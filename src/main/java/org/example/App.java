package org.example;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) throws IOException {

        //Specify the path to your file
        String file = "C:\\Users\\User\\Desktop\\data.csv";
        List<Transaction> transactions = new ArrayList<>();
        CsvToBean csv = new CsvToBean();
        CSVReader csvReader = new CSVReader(new FileReader(file), ',', '"', 1);
        List list = csv.parse(setColumnMapping(), csvReader);
        for (Object object : list) {
            TransactionCsv transactionCsv = (TransactionCsv) object;
            transactions.add(new Transaction(transactionCsv));
        }

        System.out.println(getSpecificReport(transactions));

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy setColumnMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(TransactionCsv.class);
        String[] columns = new String[]{"id", "date", "amount", "merchant", "type", "relatedTransaction"};
        strategy.setColumnMapping(columns);
        return strategy;
    }

    private static Report getSpecificReport(List<Transaction> transactions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(">> from Date: ");
        String fromDateStr = scanner.nextLine();
        LocalDateTime fromDate = LocalDateTime.parse(fromDateStr, DATE_TIME_FORMATTER);
        System.out.println(">> to Date: ");
        String toDateStr = scanner.nextLine();
        LocalDateTime toDate = LocalDateTime.parse(toDateStr, DATE_TIME_FORMATTER);
        System.out.println(">> Merchant: ");
        String merchant = scanner.nextLine();
        scanner.close();

        return Analyser.analyze(transactions, fromDate, toDate, merchant);
    }
}
