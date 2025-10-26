package com.miniproject.budgetrecord.category.repository;

import com.miniproject.budgetrecord.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUser_Id(Long userId);
    boolean existsByUser_IdAndName(Long userId, String name);
    Optional<Category> findByIdAndUser_Id(Long id, Long userId);
}