package com.example.be_restaurant.controller;

import com.example.be_restaurant.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bill")
public class BillController {
    private final BillService billService;

    @PostMapping("/{deskId}/{payment}")
    public ResponseEntity<?> createBill(@PathVariable("deskId") Long deskId,
                                        @PathVariable("payment") String payment) {
        billService.createBill(deskId, payment);
        return ResponseEntity.ok("Created bill");
    }
}
