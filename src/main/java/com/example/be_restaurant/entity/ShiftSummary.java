package com.example.be_restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "shift_summary")
@NoArgsConstructor
public class ShiftSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_before")
    private Double totalBefore;

    @Column(name = "total_discount")
    private Double totalDiscount;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "total_order")
    private Integer totalOrder;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;

    @OneToMany(mappedBy = "shiftSummary")
    private List<ShiftSoldItem> shiftSoldItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "daily_summary_id")
    private DailySummary dailySummary;
}
