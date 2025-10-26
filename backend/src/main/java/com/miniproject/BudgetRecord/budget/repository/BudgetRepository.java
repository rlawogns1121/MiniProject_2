package com.miniproject.budgetrecord.budget.repository;

import com.miniproject.budgetrecord.budget.domain.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUser_IdAndYearAndMonth(Long userId, short year, byte month);
}