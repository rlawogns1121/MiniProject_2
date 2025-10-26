package com.miniproject.budgetrecord.transaction.controller;

import com.miniproject.budgetrecord.common.ApiResponse;
import com.miniproject.budgetrecord.transaction.dto.TransactionCreateRequest;
import com.miniproject.budgetrecord.transaction.dto.TransactionResponse;
import com.miniproject.budgetrecord.transaction.service.TransactionService;
import com.miniproject.budgetrecord.user.domain.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService service;

    // 오늘은 데모 유저 고정 (JWT 붙이면 제거)
    private User mockUser() { return User.builder().id(1L).email("demo@test.com").build(); }

    @PostMapping
    public ApiResponse<TransactionResponse> create(@RequestBody @Valid TransactionCreateRequest req) {
        return ApiResponse.ok(service.create(mockUser(), req));
    }

    @GetMapping
    public ApiResponse<List<TransactionResponse>> list(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ApiResponse.ok(service.list(mockUser(), from, to));
    }
}