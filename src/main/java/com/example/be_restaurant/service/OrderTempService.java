package com.example.be_restaurant.service;

import com.example.be_restaurant.bean.request.OrderTempRequest;
import com.example.be_restaurant.bean.response.OrderResponse;
import com.example.be_restaurant.entity.OrderTemp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderTempService {
    void createOrderTemp(OrderTemp orderTemp);
    void deleteOrderTempByDeskId(Long deskId);
}
