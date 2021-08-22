package com.example.onlineshop.controller;

import com.example.onlineshop.common.ApiResponse;
import com.example.onlineshop.model.binding.store.ProductBindingModel;
import com.example.onlineshop.model.view.store.StoreViewModel;
import com.example.onlineshop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {


    @Autowired
    StoreService storeService;


    @GetMapping("/")
    public ModelAndView getProducts() {
        ModelAndView modelAndView = new ModelAndView("shop");
        List<StoreViewModel> store = storeService.getProductsInStock();
        modelAndView.addObject("products", store);
//        List<StoreViewModel> body = storeService.getProductsInStock();
//        return new ResponseEntity<List<StoreViewModel>>(body, HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Long productID, @RequestParam Integer quantity) {
        storeService.loadProduct(productID, quantity);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }


}
