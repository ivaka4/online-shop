package com.example.onlineshop.service;

import com.example.onlineshop.model.service.StoreServiceModel;
import com.example.onlineshop.model.view.store.StoreViewModel;

import java.util.List;

public interface StoreService {

    List<StoreViewModel> getProductsInStock ();
    List<StoreViewModel> checkProductsNotInStock();
    StoreServiceModel loadProduct(Long productId, int quantity);
    StoreServiceModel addProduct(StoreServiceModel storeServiceModel);
    StoreViewModel getSingleProduct(Long productId);

}
