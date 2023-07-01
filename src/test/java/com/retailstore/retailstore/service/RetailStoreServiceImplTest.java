package com.retailstore.retailstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.retailstore.retailstore.repository.RetailStoreRepository;
import com.retailstore.retailstore.repository.entity.RetailStoreDao;

public class RetailStoreServiceImplTest {
    
    @Mock
    private RetailStoreRepository retailStoreRepository;
    private RetailStoreService retailStoreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        retailStoreService = new RetailStoreServiceImpl(retailStoreRepository);
    }


    @Test
    void calculateNetPayableAmount_succeed_goldCard() {

        //mocking the data
        RetailStoreDao retailStoreDao = new RetailStoreDao();
        retailStoreDao.setCardType("Gold");
        retailStoreDao.setId("32efratj");
  
        //mock repository response
        when(retailStoreRepository.findById(anyString())).thenReturn(Optional.of(retailStoreDao));

        //perform operation
        Double response = retailStoreService.calculateNetPayableAmount(100.0, "32efratj", false);

        //verifying the response
        Double expectedDiscount = 0.3 * 100 + (int) (100 / 200) * 5;
        Double expectedResponse = 100 - expectedDiscount;
        assertEquals(response, expectedResponse);


    }

    @Test
    void calculateNetPayableAmount_succeed_silverCard() {

        //mocking the data
        RetailStoreDao retailStoreDao = new RetailStoreDao();
        retailStoreDao.setCardType("Silver");
        retailStoreDao.setId("32efratj");
  
        //mock repository response
        when(retailStoreRepository.findById(anyString())).thenReturn(Optional.of(retailStoreDao));

        //perform operation
        Double response = retailStoreService.calculateNetPayableAmount(100.0, "32efratj", false);

        //verifying the response
        Double expectedDiscount = 0.2 * 100 + (int) (100 / 200) * 5;
        Double expectedResponse = 100 - expectedDiscount;
        assertEquals(expectedResponse, response);


    }

    
    @Test
    void calculateNetPayableAmount_succeed_affiliate() {

        //mocking the data
        RetailStoreDao retailStoreDao = new RetailStoreDao();
        retailStoreDao.setIsAffiliate(1);
        retailStoreDao.setId("32efratj");
        retailStoreDao.setCardType("");
  
        //mock repository response
        when(retailStoreRepository.findById(anyString())).thenReturn(Optional.of(retailStoreDao));

        //perform operation
        Double response = retailStoreService.calculateNetPayableAmount(100.0, "32efratj", false);

        //verifying the response
        Double expectedDiscount = 0.1 * 100 + (int) (100 / 200) * 5;
        Double expectedResponse = 100 - expectedDiscount;
        assertEquals(expectedResponse, response);


    }

    @Test
    void calculateNetPayableAmount_succeed_member() {

        //mocking the data
        RetailStoreDao retailStoreDao = new RetailStoreDao();
        retailStoreDao.setMembershipYears(3);
        retailStoreDao.setId("32efratj");
        retailStoreDao.setCardType("");
  
        //mock repository response
        when(retailStoreRepository.findById(anyString())).thenReturn(Optional.of(retailStoreDao));

        //perform operation
        Double response = retailStoreService.calculateNetPayableAmount(100.0, "32efratj", false);

        //verifying the response
        Double expectedDiscount = 0.05 * 100 + (int) (100 / 200) * 5;
        Double expectedResponse = 100 - expectedDiscount;
        assertEquals(expectedResponse, response);


    }

    @Test
    void calculateNetPayableAmount_succeed_isBoughtPhoneTrue() {

        //mocking the data
        RetailStoreDao retailStoreDao = new RetailStoreDao();
        retailStoreDao.setMembershipYears(3);
        retailStoreDao.setId("32efratj");
        retailStoreDao.setCardType("Silver");
  
        //mock repository response
        when(retailStoreRepository.findById(anyString())).thenReturn(Optional.of(retailStoreDao));

        //perform operation
        Double response = retailStoreService.calculateNetPayableAmount(100.0, "32efratj", true);

        //verifying the response
        Double expectedResponse = 100.0 - (int) (100 / 200) * 5;
        assertEquals(expectedResponse, response);


    }

    @Test
    void calculateNetPayableAmount_throwNoSuchElementException() {
                
        //mocking the data
        String userId = "fdgfhh1324";
        double bill = 100.0;
        boolean isBoughtPhone = false;

        //mock repository response
        when(retailStoreRepository.findById(userId)).thenReturn(Optional.empty());

        //verifying the response
        assertThrows(NoSuchElementException.class, () ->
                retailStoreService.calculateNetPayableAmount(bill, userId, isBoughtPhone));
    }
}



