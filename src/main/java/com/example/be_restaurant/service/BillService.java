package com.example.be_restaurant.service;

import com.example.be_restaurant.bean.request.OrderTempRequest;
import com.example.be_restaurant.entity.Bill;
import com.example.be_restaurant.entity.OrderTemp;
import org.springframework.stereotype.Service;

@Service
public interface BillService {
    Bill createBill(Long shiftId, Long deskId, String payment);
    byte[] generateKitchenInvoicePdf(OrderTempRequest orderTempRequest);
    byte[] generatePaymentInvoicePdf(Long billId);
}
