package com.miniproject.budgetrecord.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TransactionResponse {
    private Long id;
    private String date;
    private int amount;
    private Long categoryId;
    private String categoryName;
    private String memo;
}