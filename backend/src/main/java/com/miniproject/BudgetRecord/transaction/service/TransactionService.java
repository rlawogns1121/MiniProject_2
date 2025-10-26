package com.miniproject.budgetrecord.transaction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.budgetrecord.category.domain.entity.Category;
import com.miniproject.budgetrecord.category.repository.CategoryRepository;
import com.miniproject.budgetrecord.transaction.domain.entity.Transaction;
import com.miniproject.budgetrecord.transaction.dto.TransactionCreateRequest;
import com.miniproject.budgetrecord.transaction.dto.TransactionResponse;
import com.miniproject.budgetrecord.transaction.repository.TransactionRepository;
import com.miniproject.budgetrecord.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository txRepo;
    private final CategoryRepository catRepo;

    @Transactional
    public TransactionResponse create(User user, TransactionCreateRequest req) {
        Category cat = catRepo.findByIdAndUser_Id(req.getCategoryId(), user.getId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        LocalDate date = LocalDate.parse(req.getDate());

        Transaction saved = txRepo.save(Transaction.builder()
                .user(user)
                .category(cat)
                .date(date)
                .amount(req.getAmount())
                .memo(req.getMemo())
                .build());

        return TransactionResponse.builder()
                .id(saved.getId())
                .date(saved.getDate().toString())
                .amount(saved.getAmount())
                .categoryId(cat.getId())
                .categoryName(cat.getName())
                .memo(saved.getMemo())
                .build();
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> list(User user, LocalDate from, LocalDate to) {
        return txRepo.findByUser_IdAndDateBetweenOrderByDateAsc(user.getId(), from, to)
                .stream()
                .map(t -> TransactionResponse.builder()
                        .id(t.getId())
                        .date(t.getDate().toString())
                        .amount(t.getAmount())
                        .categoryId(t.getCategory().getId())
                        .categoryName(t.getCategory().getName())
                        .memo(t.getMemo())
                        .build())
                .toList();
    }
}