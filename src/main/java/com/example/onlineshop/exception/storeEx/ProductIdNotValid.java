package com.example.onlineshop.exception.storeEx;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "There is no product with this id")
public class ProductIdNotValid extends RuntimeException{
    public ProductIdNotValid(String message) {
        super(message);
    }
}
