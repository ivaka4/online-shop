package com.example.onlineshop.controller;

import com.example.onlineshop.common.ApiResponse;
import com.example.onlineshop.exception.cartEx.CartProductNotExistException;
import com.example.onlineshop.exception.storeEx.ProductIdNotValid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({CartProductNotExistException.class,
            ProductIdNotValid.class})
    @GetMapping("/error")
    public ResponseEntity<ApiResponse> handleUserException(HttpServletRequest req) {
       return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Something went wrong, try again."),
               HttpStatus.INTERNAL_SERVER_ERROR);
    }
}