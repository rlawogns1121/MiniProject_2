package com.miniproject.budgetrecord.category.controller;

import com.miniproject.budgetrecord.category.dto.CategoryRequest;
import com.miniproject.budgetrecord.category.dto.CategoryResponse;
import com.miniproject.budgetrecord.category.service.CategoryService;
import com.miniproject.budgetrecord.common.ApiResponse;
import com.miniproject.budgetrecord.user.domain.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService service;

    // 오늘은 데모 유저 고정 (내일 JWT 교체)
    private User mockUser() {
        return User.builder().id(1L).email("demo@test.com").build();
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> list() {
        return ApiResponse.ok(service.list(mockUser()));
    }

    @PostMapping
    public ApiResponse<CategoryResponse> create(@RequestBody @Valid CategoryRequest req) {
        return ApiResponse.ok(service.create(mockUser(), req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(mockUser(), id);
        return ApiResponse.ok();
    }
}