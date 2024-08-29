package com.example.springbootupbasicjpql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersCustomerDto {
    private int ordersId;
    private String customerName;
    private String customerPhone;
    private LocalDate orderDate;

}
