package com.miniproject.budgetrecord.category.dto;

import lombok.*;

@Getter @AllArgsConstructor @Builder
public class CategoryResponse {
  private Long id;
  private String name;
}