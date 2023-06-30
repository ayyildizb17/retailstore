package com.retailstore.retailstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.retailstore.retailstore.service.RetailStoreService;



@RestController
public class RetailStoreController {
    
    @Autowired
    private RetailStoreService retailStoreService;

    RetailStoreController(RetailStoreService retailStoreService) {
        this.retailStoreService = retailStoreService;
    }

    @GetMapping("/netPayableAmount")
    public ResponseEntity<RetailStoreApiResponse> calculateNetPayableAmount(@RequestBody RetailStoreApiRequest request) {

        RetailStoreApiResponse response = new RetailStoreApiResponse();
        response.setNetPayableAmount(retailStoreService.calculateNetPayableAmount(
           request.getBill(), request.getUserId(), request.isBoughtPhone()));
        return new ResponseEntity<>(response, HttpStatus.OK);
   }
}
