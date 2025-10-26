package com.miniproject.budgetrecord.transaction.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionCreateRequest {
    @NotBlank(message = "날짜(YYYY-MM-DD)를 입력하세요.")
    private String date;

    @Min(value = 0, message = "금액은 0 이상이어야 합니다.")
    private int amount;

    @NotNull(message = "카테고리 ID가 필요합니다.")
    private Long categoryId;

    private String memo;
}