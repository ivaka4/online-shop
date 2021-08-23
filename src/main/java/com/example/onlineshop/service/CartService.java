package com.example.onlineshop.service;

import com.example.onlineshop.model.binding.cart.AddToCartBinding;
import com.example.onlineshop.model.entity.CartEntity;
import com.example.onlineshop.model.entity.StoreEntity;
import com.example.onlineshop.model.service.StoreServiceModel;
import com.example.onlineshop.model.view.cart.CartProductViewModel;
import com.example.onlineshop.model.view.cart.CartViewModel;

public interface CartService {

    void addToCart(AddToCartBinding addToCartBinding, StoreServiceModel product);

    CartViewModel listCartItems();

    CartProductViewModel getViewFromCart(CartEntity cartEntity);

    void updateCartProduct (AddToCartBinding addToCartBinding, StoreServiceModel product);

    void deleteCartProduct(long id);

}
