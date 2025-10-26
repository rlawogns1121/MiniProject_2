package com.miniproject.budgetrecord.budget.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetUpsertRequest {
    @NotNull
    @Min(0)
    private Integer totalAmount;
}