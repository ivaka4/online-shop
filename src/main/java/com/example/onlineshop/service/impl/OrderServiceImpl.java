package com.example.onlineshop.service.impl;

import com.example.onlineshop.model.entity.OrderEntity;
import com.example.onlineshop.model.entity.StoreEntity;
import com.example.onlineshop.model.view.cart.CartViewModel;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.StoreRepository;
import com.example.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    private final StoreRepository storeRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CartRepository cartRepository, StoreRepository storeRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public void completeOrder(CartViewModel cart) {
        OrderEntity orderEntity;
        for (int i = 0; i < cart.getCartItems().size(); i++) {
            orderEntity = new OrderEntity();
            orderEntity.setProductId(cart.getCartItems().get(i).getProduct().getId());
            StoreEntity product = this.storeRepository.getById(cart.getCartItems().get(i).getProduct().getId());
            if (cart.getCartItems().get(i).getQuantity() < cart.getCartItems().get(i).getProduct().getQuantity()) {

                product.setQuantity(Math.max(product.getQuantity() - cart.getCartItems().get(i).getQuantity(), 0));
                this.storeRepository.saveAndFlush(product);
                orderEntity.setQuantity(cart.getCartItems().get(i).getQuantity());
                orderEntity.setCompleted(true);
            } else {
                product.setQuantity(Math.max(product.getQuantity() - cart.getCartItems().get(i).getQuantity(), 0));
                this.storeRepository.saveAndFlush(product);
                orderEntity.setCompleted(false);
                orderEntity.setQuantity(0);
                orderEntity.setNeededQuantity(cart.getCartItems().get(i).getQuantity() - cart.getCartItems().get(i
                ).getProduct().getQuantity());
            }
            this.orderRepository.saveAndFlush(orderEntity);
            this.cartRepository.deleteAll();
        }
    }
}
