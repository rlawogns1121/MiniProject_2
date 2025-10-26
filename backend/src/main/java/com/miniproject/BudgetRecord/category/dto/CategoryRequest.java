package com.miniproject.budgetrecord.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
public class CategoryRequest {
  @NotBlank(message = "카테고리명을 입력해주세요.")
  private String name;
}