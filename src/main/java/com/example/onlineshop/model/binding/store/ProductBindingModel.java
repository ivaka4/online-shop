package com.example.onlineshop.model.binding.store;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public class ProductBindingModel {
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1,message = "Quantity must be positive number")
    private  Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
