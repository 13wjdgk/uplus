package com.example.springbootupbasicjpql.dto;

import com.example.springbootupbasicjpql.entity.Orders;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class ProductDto {

    private int id;
    private String name;
    private int price;
    private int quantity;


    private List<Orders> orders;
}
