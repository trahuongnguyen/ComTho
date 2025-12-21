package com.example.be_restaurant.controller;

import com.example.be_restaurant.entity.Bill;
import com.example.be_restaurant.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

    @PostMapping("/{shiftId}/{deskId}/{payment}")
    public ResponseEntity<byte[]> createBill(
            @PathVariable Long shiftId,
            @PathVariable Long deskId,
            @PathVariable String payment) {

        Bill bill = billService.createBill(shiftId, deskId, payment);
        byte[] pdfBytes = billService.generatePaymentInvoicePdf(bill.getId());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=payment-bill.pdf")
                .body(pdfBytes);
    }


}
