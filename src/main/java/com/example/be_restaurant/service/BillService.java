package com.example.be_restaurant.service;

import com.example.be_restaurant.entity.OrderTemp;
import org.springframework.stereotype.Service;

@Service
public interface BillService {
    void createBill(Long deskId, String payment);
}
