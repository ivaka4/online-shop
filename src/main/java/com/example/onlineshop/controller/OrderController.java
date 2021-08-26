package com.example.onlineshop.controller;

import com.example.onlineshop.common.ApiResponse;
import com.example.onlineshop.model.view.cart.CartViewModel;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;


    @PostMapping("/complete")
    public ResponseEntity<ApiResponse> placeOrder() {
        CartViewModel cart = this.cartService.listCartItems();
        orderService.completeOrder(cart);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Request has been accepted"), HttpStatus.CREATED);
    }
}
