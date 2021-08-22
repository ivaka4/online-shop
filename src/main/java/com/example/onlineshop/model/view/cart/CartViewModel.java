package com.example.onlineshop.model.view.cart;

import java.util.List;

public class CartViewModel {
    private List<CartProductViewModel> cartItems;

    public CartViewModel(List<CartProductViewModel> cartItemDtoList) {
        this.cartItems = cartItemDtoList;

    }

    public List<CartProductViewModel> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartProductViewModel> cartItemDtoList) {
        this.cartItems = cartItemDtoList;
    }
}
