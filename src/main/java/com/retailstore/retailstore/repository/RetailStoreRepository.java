package com.retailstore.retailstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.retailstore.retailstore.repository.entity.RetailStoreDao;

@Repository
public interface RetailStoreRepository extends JpaRepository<RetailStoreDao, String>{
    
}
