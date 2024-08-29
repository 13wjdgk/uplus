package com.example.springbootupbasicjpql.service;

import com.example.springbootupbasicjpql.dto.CustomerDto;
import com.example.springbootupbasicjpql.dto.OrdersCustomerDto;
import com.example.springbootupbasicjpql.dto.OrdersCustomerProductDto;
import com.example.springbootupbasicjpql.dto.OrdersDto;
import com.example.springbootupbasicjpql.entity.Customer;
import com.example.springbootupbasicjpql.entity.Orders;
import com.example.springbootupbasicjpql.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService{
    private final OrdersRepository ordersRepository;
    // #1
    @Override
    public List<Orders> listOrders1(char gender) {
        return ordersRepository.listOrders1(gender);
    }


    // #2
    @Override
    public List<Orders> listOrders2(char gender) {
        return ordersRepository.listOrders1(gender);
    }

    @Override
    public List<OrdersDto> listOrders3(char gender) {
        List<Orders> ordersList = ordersRepository.listOrders1(gender);
        List<OrdersDto> ordersDtoList = new ArrayList<>();

        ordersList.forEach(orders -> {
            OrdersDto ordersDto = new OrdersDto();
            ordersDto.setId(orders.getId());
            ordersDto.setOrderDate(orders.getOrderDate());
            ordersDto.setOrderQuantity(orders.getOrderQuantity());
            ordersDtoList.add(ordersDto);
        });
        return ordersDtoList;
    }

    @Override
    public List<OrdersDto> listOrders4(char gender) {
        List<Orders> ordersList = ordersRepository.listOrders4(gender);
        List<OrdersDto> ordersDtoList = new ArrayList<>();

        ordersList.forEach(orders -> {
            OrdersDto ordersDto = new OrdersDto();
            ordersDto.setId(orders.getId());
            ordersDto.setOrderDate(orders.getOrderDate());
            ordersDto.setOrderQuantity(orders.getOrderQuantity());
            Customer customer = orders.getCustomer();
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customer.getId());
            customerDto.setName(customer.getName());
            customerDto.setPhone(customer.getPhone());
            customerDto.setGender(customer.getGender());
            ordersDto.setCustomerDto(customerDto);
            ordersDtoList.add(ordersDto);
        });
        return ordersDtoList;
    }
    @Override
    public List<OrdersCustomerDto> listOrdersCustomer1() {
        return ordersRepository.listOrdersCustomer1();
    }

    @Override
    public List<OrdersDto> listOrders1Dto(char gender) {
        return ordersRepository.listOrders1Dto(gender);
    }
    @Override
    public List<OrdersCustomerProductDto> listOrdersCustomerProduct(char gender,int price) {
        return ordersRepository.listOrdersCustomerProduct(gender,price);
    }
}
