package com.miniproject.budgetrecord.budget.service;

import com.miniproject.budgetrecord.budget.domain.entity.Budget;
import com.miniproject.budgetrecord.budget.dto.*;
import com.miniproject.budgetrecord.budget.repository.BudgetRepository;
import com.miniproject.budgetrecord.transaction.repository.TransactionRepository;
import com.miniproject.budgetrecord.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public BudgetResponse get(User user, int year, int month) {
        Budget budget = budgetRepository
                .findByUser_IdAndYearAndMonth(user.getId(), (short) year, (byte) month)
                .orElse(Budget.builder()
                        .user(user)
                        .year((short) year)
                        .month((byte) month)
                        .totalAmount(0)
                        .build());

        int spent = transactionRepository.sumByMonth(user.getId(), year, month);
        double usageRate = budget.getTotalAmount() == 0 ? 0 : (spent * 100.0 / budget.getTotalAmount());

        return BudgetResponse.builder()
                .year(year)
                .month(month)
                .totalAmount(budget.getTotalAmount())
                .spent(spent)
                .usageRate(Math.round(usageRate * 100) / 100.0)
                .build();
    }

    @Transactional
    public BudgetResponse upsert(User user, int year, int month, BudgetUpsertRequest req) {
        Budget budget = budgetRepository
                .findByUser_IdAndYearAndMonth(user.getId(), (short) year, (byte) month)
                .orElse(Budget.builder()
                        .user(user)
                        .year((short) year)
                        .month((byte) month)
                        .totalAmount(0)
                        .build());

        budget.setTotalAmount(req.getTotalAmount());
        Budget saved = budgetRepository.save(budget);

        int spent = transactionRepository.sumByMonth(user.getId(), year, month);
        double usageRate = saved.getTotalAmount() == 0 ? 0 : (spent * 100.0 / saved.getTotalAmount());

        return BudgetResponse.builder()
                .year(year)
                .month(month)
                .totalAmount(saved.getTotalAmount())
                .spent(spent)
                .usageRate(Math.round(usageRate * 100) / 100.0)
                .build();
    }
}