package com.miniproject.budgetrecord.budget.controller;

import com.miniproject.budgetrecord.budget.dto.*;
import com.miniproject.budgetrecord.budget.service.BudgetService;
import com.miniproject.budgetrecord.common.ApiResponse;
import com.miniproject.budgetrecord.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/budgets")
public class BudgetController {

    private final BudgetService service;

    private User mockUser() {
        return User.builder().id(1L).email("demo@test.com").build();
    }

    @GetMapping("/{year}/{month}")
    public ApiResponse<BudgetResponse> get(@PathVariable int year, @PathVariable int month) {
        return ApiResponse.ok(service.get(mockUser(), year, month));
    }

    @PutMapping("/{year}/{month}")
    public ApiResponse<BudgetResponse> upsert(@PathVariable int year, @PathVariable int month,
                                              @RequestBody @Valid BudgetUpsertRequest req) {
        return ApiResponse.ok(service.upsert(mockUser(), year, month, req));
    }
}