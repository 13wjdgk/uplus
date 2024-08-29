package com.example.springbootupbasicjpql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersCustomerProductDto {
    private int orderId;
    private String customerName;
    private String customerPhone;
    private String productName;
    private int productPrice;
    private String productCountry;
    private LocalDate orderDate;
}
