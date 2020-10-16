package org.example;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCsv {

    private String id;
    private String date;
    private String amount;
    private String merchant;
    private String type;
    private String relatedTransaction;

}
