package com.example.onlineshop.service.impl;

import com.example.onlineshop.model.view.cart.CartViewModel;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void completeOrder(CartViewModel cart) {

    }
}
