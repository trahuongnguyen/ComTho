package com.example.be_restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "order_detail")
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Order orders;

    @Column(name = "food")
    private String food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "up_size_option_id")
    private UpSizeOption upSizeOption;
}
