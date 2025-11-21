package com.example.be_restaurant.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "daily_sold_item")
@NoArgsConstructor
public class DailySoldItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food")
    private String food;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "discount")
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "daily_summary_id")
    private DailySummary dailySummary;
}