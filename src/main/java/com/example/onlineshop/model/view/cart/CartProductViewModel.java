package com.example.onlineshop.model.view.cart;

import com.example.onlineshop.model.entity.CartEntity;
import com.example.onlineshop.model.entity.StoreEntity;

import javax.validation.constraints.NotNull;

public class CartProductViewModel {
    private Long id;
    private @NotNull Integer quantity;
    private @NotNull StoreEntity product;

    public CartProductViewModel() {
    }

    public CartProductViewModel(CartEntity cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productName=" + product.getProductName() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public StoreEntity getProduct() {
        return product;
    }

    public void setProduct(StoreEntity product) {
        this.product = product;
    }
}
