package com.example.springbootupbasicjpql.dto;

import com.example.springbootupbasicjpql.entity.Customer;
import com.example.springbootupbasicjpql.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {

    private int id;
    private CustomerDto customerDto;
    private ProductDto productDto;
    private int orderQuantity;
    private LocalDate orderDate;

    public OrdersDto(int id, int orderQuantity, LocalDate orderDate) {
        this.id = id;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
    }
}
