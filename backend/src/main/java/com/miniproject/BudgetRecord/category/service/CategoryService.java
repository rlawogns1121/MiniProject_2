package com.miniproject.budgetrecord.category.service;

import com.miniproject.budgetrecord.category.domain.entity.Category;
import com.miniproject.budgetrecord.category.dto.*;
import com.miniproject.budgetrecord.category.repository.CategoryRepository;
import com.miniproject.budgetrecord.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repo;

    @Transactional
    public CategoryResponse create(User user, CategoryRequest req) {
        if (repo.existsByUser_IdAndName(user.getId(), req.getName()))
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");

        Category category = Category.builder()
                .user(user)
                .name(req.getName())
                .build();

        Category saved = repo.save(category);
        return new CategoryResponse(saved.getId(), saved.getName());
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> list(User user) {
        return repo.findAllByUser_Id(user.getId())
                .stream()
                .map(c -> new CategoryResponse(c.getId(), c.getName()))
                .toList();
    }

    @Transactional
    public void delete(User user, Long id) {
        Category category = repo.findByIdAndUser_Id(id, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        repo.delete(category);
    }
}