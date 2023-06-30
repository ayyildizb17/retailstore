package com.retailstore.retailstore.service;

import com.retailstore.retailstore.repository.RetailStoreRepository;
import com.retailstore.retailstore.repository.entity.RetailStoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RetailStoreServiceImpl implements RetailStoreService {

    private final RetailStoreRepository retailStoreRepository;

    @Autowired
    public RetailStoreServiceImpl(RetailStoreRepository retailStoreRepository) {
        this.retailStoreRepository = retailStoreRepository;
    }

    @Override
    public double calculateNetPayableAmount(Double bill, String userId, Boolean isBoughtPhone) {
        Optional<RetailStoreDao> userOptional = retailStoreRepository.findById(userId);

        if (userOptional.isPresent()) {
            RetailStoreDao user = userOptional.get();
            Double tempDiscount = 0.0;
            //if customer bought a phone, the percentage based discounts cant be applied
            if (user.getCardType() != null && Boolean.FALSE.equals(isBoughtPhone)) {
                if (user.getCardType().equals("Gold")) {
                    tempDiscount = 0.3;
                } else if (user.getCardType().equals("Silver")) {
                    tempDiscount = 0.2;
                } else if (user.getIsAffiliate() == 1) {
                    tempDiscount = 0.1;
                } else if (user.getMembershipYears() > 2) {
                    tempDiscount = 0.05;
                }
            }

            //calculate the total amount of discount
            Double discount = tempDiscount * bill + (int) (bill / 200) * 5;

            //return the net payable amount
            return bill - discount;
        } else {
            throw new NoSuchElementException("User not found");
        }
    }
}
