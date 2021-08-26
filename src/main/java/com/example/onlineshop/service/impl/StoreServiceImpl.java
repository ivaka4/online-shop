package com.example.onlineshop.service.impl;

import com.example.onlineshop.exception.storeEx.ProductIdNotValid;
import com.example.onlineshop.model.entity.OrderEntity;
import com.example.onlineshop.model.entity.StoreEntity;
import com.example.onlineshop.model.service.StoreServiceModel;
import com.example.onlineshop.model.view.store.StoreViewModel;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.StoreRepository;
import com.example.onlineshop.service.StoreService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {


    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, ModelMapper modelMapper, OrderRepository orderRepository) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }


    @Override
    public List<StoreViewModel> getProductsInStock() {
        List<StoreViewModel> inStockProducts = this.modelMapper
                .map(this
                                .storeRepository
                                .findAllByQuantityGreaterThan(0),
                        new TypeToken<List<StoreViewModel>>() {
                        }.getType());
        return inStockProducts;
    }

    @Override
    public List<StoreViewModel> checkProductsNotInStock() {
        return null;
    }

    @Override
    public StoreServiceModel loadProduct(Long productId, int quantity) {
        StoreEntity product = this.storeRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ProductIdNotValid("There is no product with this id: " + productId);
        }
        List<OrderEntity> orderEntity = this.orderRepository.getOrderEntityByProductId(productId);
        product.setQuantity(product.getQuantity() + quantity);
        for (int i = 0; i < orderEntity.size(); i++) {
            if (product.getQuantity() > orderEntity.get(i).getNeededQuantity()) {
                orderEntity.get(i).setQuantity(orderEntity.get(i).getNeededQuantity());
                product.setQuantity(product.getQuantity() - orderEntity.get(i).getNeededQuantity());
                orderEntity.get(i).setNeededQuantity(0);
                orderEntity.get(i).setCompleted(true);
                this.orderRepository.saveAndFlush(orderEntity.get(i));
            }

        }
        return this.modelMapper.map(this.storeRepository.saveAndFlush(product), StoreServiceModel.class);
    }

    @Override
    public StoreServiceModel addProduct(StoreServiceModel storeServiceModel) {
        StoreEntity newProduct = this.modelMapper.map(storeServiceModel, StoreEntity.class);
        if (newProduct.getProductName() == null || newProduct.getQuantity() == 0) {
            throw new ProductIdNotValid("Cannot persist model to the database");
        }
        return this.modelMapper.map(this.storeRepository.saveAndFlush(newProduct), StoreServiceModel.class);
    }

    @Override
    public StoreViewModel getSingleProduct(Long productId) {
        if (!this.storeRepository.existsById(productId)) {
            throw new ProductIdNotValid("There is not a product with this id: " + productId);
        }
        return this.modelMapper.map(this.storeRepository.findById(productId).get(), StoreViewModel.class);
    }
}
