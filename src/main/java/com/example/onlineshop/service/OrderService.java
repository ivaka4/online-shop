package com.example.onlineshop.service;

import com.example.onlineshop.model.view.cart.CartViewModel;

public interface OrderService {

    void completeOrder(CartViewModel cart);

}
