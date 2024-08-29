package com.example.springbootupbasicjpql.service;

import com.example.springbootupbasicjpql.dto.OrdersCustomerDto;
import com.example.springbootupbasicjpql.dto.OrdersCustomerProductDto;
import com.example.springbootupbasicjpql.dto.OrdersDto;
import com.example.springbootupbasicjpql.entity.Orders;

import java.util.List;

public interface OrdersService {
    // #1
    List<Orders> listOrders1(char gender);

    // #2
    List<Orders> listOrders2(char gender);

    // #3
    List<OrdersDto>  listOrders3(char gender);
    // #3
    List<OrdersDto>  listOrders4(char gender);

    List<OrdersCustomerDto> listOrdersCustomer1();

    // #6
    List<OrdersDto> listOrders1Dto(char gender);

    List<OrdersCustomerProductDto> listOrdersCustomerProduct(char gender, int price);
}
