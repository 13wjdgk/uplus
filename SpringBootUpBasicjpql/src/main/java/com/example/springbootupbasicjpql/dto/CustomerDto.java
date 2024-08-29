package com.example.springbootupbasicjpql.dto;

import com.example.springbootupbasicjpql.entity.Orders;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class CustomerDto {
    private int id;
    private String name;
    private String phone;
    private char gender;

    private List<Orders> orders;
}
