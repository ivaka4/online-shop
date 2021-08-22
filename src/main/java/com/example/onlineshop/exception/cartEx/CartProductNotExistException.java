package com.example.onlineshop.exception.cartEx;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "There is no product with this id")
public class CartProductNotExistException extends IllegalArgumentException {
    public CartProductNotExistException(String s) {
        super(s);
    }
}
