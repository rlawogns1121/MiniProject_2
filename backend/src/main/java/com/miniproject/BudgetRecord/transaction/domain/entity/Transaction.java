package com.miniproject.budgetrecord.transaction.domain.entity;

import com.miniproject.budgetrecord.category.domain.entity.Category;
import com.miniproject.budgetrecord.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(
    name = "transactions",
    indexes = {
        @Index(name = "idx_tx_user_date", columnList = "user_id,date"),
        @Index(name = "idx_tx_user_ym", columnList = "user_id,year,month")
    }
)
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int amount;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Column(nullable = false)
    private short year;

    @Column(nullable = false)
    private byte month;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) createdAt = now;
        if (updatedAt == null) updatedAt = now;
        fillYearMonth();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        fillYearMonth();
    }

    private void fillYearMonth() {
        if (date != null) {
            this.year = (short) date.getYear();
            this.month = (byte) date.getMonthValue();
        }
    }
}