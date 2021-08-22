package com.example.onlineshop.controller;

import com.example.onlineshop.common.ApiResponse;
import com.example.onlineshop.model.binding.store.NewProductBindingModel;
import com.example.onlineshop.model.binding.store.ProductBindingModel;
import com.example.onlineshop.model.view.store.StoreViewModel;
import com.example.onlineshop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/update/{productID}")
    public ModelAndView updateProductGET(@PathVariable("productID") Long productId, Model model) {
        ModelAndView modelAndView = new ModelAndView("load-product");

        StoreViewModel productViewModel = this.storeService.getSingleProduct(productId);
        modelAndView.addObject("productEditModel", productViewModel);
        if (!model.containsAttribute("productEditBindingModel")) {
            modelAndView.addObject("productEditBindingModel", new ProductBindingModel());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/load/{productID}", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Long productID, @ModelAttribute @Valid ProductBindingModel productBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Quantity is not valid"), HttpStatus.BAD_REQUEST);
        }
        storeService.loadProduct(productID, productBindingModel.getQuantity());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @RequestMapping(value = "/product/new", method = RequestMethod.GET)
    public ModelAndView newProductGet(Model model) {
        ModelAndView modelAndView = new ModelAndView("new-product");
        if (!model.containsAttribute("productEditBindingModel")) {
            modelAndView.addObject("productEditBindingModel", new NewProductBindingModel());
        }
        return modelAndView;
    }


}
