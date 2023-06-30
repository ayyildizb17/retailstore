package com.retailstore.retailstore.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "user_user")
@Data
public class RetailStoreDao {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "is_affiliate")
    private int isAffiliate;
    @Column(name = "membership_years")
    private int membershipYears;


   
}

