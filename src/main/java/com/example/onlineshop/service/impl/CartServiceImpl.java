package com.example.onlineshop.service.impl;

import com.example.onlineshop.exception.cartEx.CartProductNotExistException;
import com.example.onlineshop.model.binding.cart.AddToCartBinding;
import com.example.onlineshop.model.entity.CartEntity;
import com.example.onlineshop.model.entity.StoreEntity;
import com.example.onlineshop.model.service.StoreServiceModel;
import com.example.onlineshop.model.view.cart.CartProductViewModel;
import com.example.onlineshop.model.view.cart.CartViewModel;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.service.CartService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addToCart(AddToCartBinding addToCartBinding, StoreServiceModel product) {
        CartEntity cart = new CartEntity(this.modelMapper.map(product, StoreEntity.class), addToCartBinding.getQuantity());
        if (product.getId() == null || product.getProductName() == null || addToCartBinding.getQuantity() == 0){
            throw new CartProductNotExistException("Cannot persist product to the cart");
        }
        cart.setCreatedDate(new Date());
        this.cartRepository.saveAndFlush(cart);
    }

    @Override
    public CartViewModel listCartItems() {
        List<CartEntity> cart = this.cartRepository.findAll();
        List<CartProductViewModel> viewModels = this.modelMapper
                .map(cart,
                        new TypeToken<List<CartProductViewModel>>(){}
                .getType());
        return new CartViewModel(viewModels);
    }

    @Override
    public CartProductViewModel getViewFromCart(CartEntity cartEntity) {
        CartProductViewModel cartProductViewModel = new CartProductViewModel(cartEntity);
        return cartProductViewModel;
    }

    @Override
    public void updateCartProduct(AddToCartBinding addToCartBinding, StoreServiceModel product) {
        CartEntity cartEntity = this.cartRepository.getById(addToCartBinding.getId());
        if (this.cartRepository.getById(addToCartBinding.getId()).getProduct().getId() != product.getId()){
            throw new CartProductNotExistException("Cannot update cart");
        }
        cartEntity.setQuantity(addToCartBinding.getQuantity());
        cartEntity.setCreatedDate(new Date());
        this.cartRepository.saveAndFlush(cartEntity);
    }

    @Override
    public void deleteCartProduct(long id) {
        if (!this.cartRepository.existsById(id)){
            throw new CartProductNotExistException("Item in the cart with this id: " + id + " not exist.");
        }
        this.cartRepository.deleteById(id);
    }
}
