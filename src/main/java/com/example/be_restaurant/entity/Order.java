package com.example.be_restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

    @Column(name = "discount")
    private Double discount;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "desk_id")
    private Desk desk;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetail> orderDetails;

    @OneToOne(mappedBy = "order")
    private Invoice invoice;

}
