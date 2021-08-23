package com.example.onlineshop.controller;

import com.example.onlineshop.common.ApiResponse;
import com.example.onlineshop.model.binding.cart.AddToCartBinding;
import com.example.onlineshop.model.entity.StoreEntity;
import com.example.onlineshop.model.service.StoreServiceModel;
import com.example.onlineshop.model.view.cart.CartViewModel;
import com.example.onlineshop.model.view.store.StoreViewModel;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@ModelAttribute AddToCartBinding addToCartDto) {
        StoreViewModel product = storeService.getSingleProduct(addToCartDto.getProductId());
        System.out.println("product to add"+  product.getProductName());
        cartService.addToCart(addToCartDto, this.modelMapper.map(product, StoreServiceModel.class));
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

    }
    @GetMapping("/")
    public ResponseEntity<CartViewModel> getCartItems() {
        CartViewModel cartDto = cartService.listCartItems();
        return new ResponseEntity<CartViewModel>(cartDto,HttpStatus.OK);
    }
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable("cartItemId")Long id, @ModelAttribute @Valid AddToCartBinding cartDto){
        StoreViewModel product = storeService.getSingleProduct(cartDto.getProductId());
        cartDto.setId(id);
        cartService.updateCartProduct(cartDto, this.modelMapper.map(product, StoreServiceModel.class));
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Cart has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Long itemID) {
        cartService.deleteCartProduct(itemID);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been removed from cart"), HttpStatus.OK);
    }
}
