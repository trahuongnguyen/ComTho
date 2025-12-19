package com.example.be_restaurant.bean.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequest {
    double discount;
    long deskId;
    List<OrderDetailRequest> orderDetails;
}

