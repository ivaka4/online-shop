package com.example.onlineshop.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "product_name")
    private String productName;

    @Column(nullable = false)
    @Min(value = 1)
    private int quantity;



}
