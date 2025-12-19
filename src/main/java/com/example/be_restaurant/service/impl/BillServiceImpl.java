package com.example.be_restaurant.service.impl;

import com.example.be_restaurant.bean.request.OrderDetailTempRequest;
import com.example.be_restaurant.bean.request.OrderTempRequest;
import com.example.be_restaurant.entity.*;
import com.example.be_restaurant.mapper.OrderTempMapper;
import com.example.be_restaurant.repository.*;
import com.example.be_restaurant.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final OrderTempRepository orderTempRepository;
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final BillRepository billRepository;

    @Override
    public void createBill(Long deskId, String payment) {
        List<OrderTemp> orderTemp = orderTempRepository.findAllByDeskId(deskId);
        List<OrderTempRequest> orderTempRequests = orderTemp.stream()
                .map(OrderTempMapper::toRequest)
                .toList();
        orderTempRequests = orderTempRequests.stream()
                .sorted(Comparator.comparing(OrderTempRequest::getStartTime))
                .toList();
        Order order = new Order();
        order.setStartTime(orderTempRequests.get(0).getStartTime());
        order.setDesk(deskId);
        order.setDiscount(orderTempRequests.stream()
                .mapToDouble(OrderTempRequest::getDiscount)
                .sum());
        Order saveOrder = orderRepository.save(order);
        for (OrderTempRequest orderTempRequest : orderTempRequests) {
            for (OrderDetailTempRequest detailTempRequest : orderTempRequest.getOrderDetails()) {
                OrderDetail orderDetail = new OrderDetail();
                Optional<Food> food = foodRepository.findByIdAndStatus(detailTempRequest.getFoodId(), true);
                orderDetail.setFood(food.map(Food::getName).orElse("Unknown Food"));
                orderDetail.setQuantity(detailTempRequest.getCount());
                orderDetail.setUpsizeOption(detailTempRequest.isCanUpSize());
                List<Food> toppingFoods = foodRepository.findAllByIdInAndStatus(detailTempRequest.getToppingId(), true);
                orderDetail.setTopping(String.join(", ", toppingFoods.stream().map(Food::getName).toList()));
                orderDetail.setOrders(saveOrder);
                orderDetail.setAmount(food.map(Food::getPrice).orElse(0.0) +
                        toppingFoods.stream().mapToDouble(Food::getPrice).sum() +
                        (detailTempRequest.isCanUpSize() ? 10000.0 : 0.0));
                orderDetailRepository.save(orderDetail);
            }
        }
        Bill bill = new Bill();
        bill.setPayment(Bill.Payment.valueOf(payment));
        bill.setOrder(saveOrder);
        bill.setShift(null);
        bill.setTotalBefore(
                orderDetailRepository.findAllByOrdersId(saveOrder.getId()).stream()
                        .mapToDouble(od -> od.getAmount() * od.getQuantity())
                        .sum()
        );
        bill.setTotalDiscount(saveOrder.getDiscount());
        bill.setTotalAmount(bill.getTotalBefore() - bill.getTotalDiscount());
        billRepository.save(bill);
        orderTempRepository.deleteAll(orderTemp);
    }
}
