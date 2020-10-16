package org.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class App {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    //Specify the path to your file
    private static final String DEFAULT_FILE_PATH = "C:\\Users\\User\\Desktop\\data.csv";

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println(">> Use default file path?: y/n ");
        String answer = scanner.nextLine();
        String filePath;
        if (answer.equals("n")) {
            System.out.println(">> Enter new file path: ");
            filePath = scanner.nextLine();
        } else {
            filePath = DEFAULT_FILE_PATH;
        }

        List<Transaction> transactions = CsvResolver.getTransactions(filePath);
        System.out.println(getSpecificReport(transactions));

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
