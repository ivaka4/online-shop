package com.example.onlineshop.repository;

import com.example.onlineshop.model.entity.StoreEntity;
import com.example.onlineshop.model.view.StoreViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    List<StoreEntity> findAllByQuantityGreaterThan(int num);

    @Query("SELECT s.quantity FROM StoreEntity as s JOIN OrderEntity as o WHERE o.isCompleted = false")
    List<StoreEntity> getRequstedNumberNotInStock();
}
