package com.retailstore.retailstore.controller;




import lombok.Getter;

@Getter
public class RetailStoreApiRequest {
    private String userId;
    private Double bill;
    private boolean isBoughtPhone;    
}
