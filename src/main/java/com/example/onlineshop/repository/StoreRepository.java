package com.example.onlineshop.repository;

import com.example.onlineshop.model.entity.StoreEntity;
import com.example.onlineshop.model.view.StoreViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    List<StoreEntity> findAllByQuantityGreaterThan(int num);
}
