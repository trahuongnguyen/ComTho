package com.example.be_restaurant.entity;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity {
    @Column(name = "status")
    private boolean status;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private User createdBy;

    @Column(name = "updated_by")
    private User updatedBy;
}
