package com.miniproject.budgetrecord.budget.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetResponse {
    private int year;
    private int month;
    private int totalAmount;
    private int spent;
    private double usageRate;
}