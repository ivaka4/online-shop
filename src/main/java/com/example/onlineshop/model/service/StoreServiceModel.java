package com.example.onlineshop.model.service;

import com.example.onlineshop.model.binding.store.NewProductBindingModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreServiceModel {

    private Long id;
    private String productName;
    private int quantity;

    public StoreServiceModel(NewProductBindingModel newProductBindingModel) {
        this.setProductName(newProductBindingModel.getProductName());
        this.setQuantity(newProductBindingModel.getQuantity());
    }
}
