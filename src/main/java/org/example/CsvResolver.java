package org.example;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvResolver {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<Transaction> getTransactions(String filePath) throws FileNotFoundException {
        List<Transaction> transactions = new ArrayList<>();
        CsvToBean csv = new CsvToBean();
        CSVReader csvReader = new CSVReader(new FileReader(filePath), ',', '"', 1);
        List list = csv.parse(setColumnMapping(), csvReader);
        for (Object object : list) {
            TransactionCsv transactionCsv = (TransactionCsv) object;
            transactions.add(new Transaction(transactionCsv));
        }
        return transactions;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy setColumnMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(TransactionCsv.class);
        String[] columns = new String[]{"id", "date", "amount", "merchant", "type", "relatedTransaction"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
