package com.miniproject.budgetrecord.transaction.repository;

import com.miniproject.budgetrecord.transaction.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser_IdAndDateBetweenOrderByDateAsc(
            Long userId, LocalDate from, LocalDate to
    );

    @Query("""
        select coalesce(sum(t.amount), 0)
        from Transaction t
        where t.user.id = :uid and t.year = :year and t.month = :month
    """)
    int sumByMonth(@Param("uid") Long userId,
                   @Param("year") int year,
                   @Param("month") int month);
}