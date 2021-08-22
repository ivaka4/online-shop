package com.example.onlineshop.model.binding.cart;
import javax.validation.constraints.NotNull;

public class AddToCartBinding {
    private Long id;
    private @NotNull Integer productId;
    private @NotNull Integer quantity;

    public AddToCartBinding() {
    }



    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ",";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
