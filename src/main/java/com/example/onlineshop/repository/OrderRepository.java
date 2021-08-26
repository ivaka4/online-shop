package com.example.onlineshop.repository;

import com.example.onlineshop.model.entity.OrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity as o WHERE o.productId=:id AND o.isCompleted IN (false)")
    List<OrderEntity> getOrderEntityByProductId(@Param("id")Long id);
}
