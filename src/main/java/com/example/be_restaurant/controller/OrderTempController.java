package com.example.be_restaurant.controller;

import com.example.be_restaurant.bean.request.OrderTempRequest;
import com.example.be_restaurant.entity.OrderTemp;
import com.example.be_restaurant.mapper.OrderTempMapper;
import com.example.be_restaurant.service.OrderTempService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderTemp")
public class OrderTempController {
    private final OrderTempService orderTempService;

    @PostMapping
    public ResponseEntity<?> createOrderTemp(
            @RequestBody OrderTempRequest orderTempRequest
    ) {
        OrderTemp orderTemp = OrderTempMapper.toEntity(orderTempRequest);
        orderTempService.createOrderTemp(orderTemp);
        return ResponseEntity.ok("Created order temp");
    }

    @DeleteMapping("/{deskId}")
    public ResponseEntity<?> deleteOrderTempByDeskId(
            @PathVariable Long deskId
    ) {
        orderTempService.deleteOrderTempByDeskId(deskId);
        return ResponseEntity.ok("Deleted order temp by deskId: " + deskId);
    }
}
