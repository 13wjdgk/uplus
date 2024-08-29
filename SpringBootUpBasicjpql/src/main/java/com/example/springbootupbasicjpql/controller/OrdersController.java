package com.example.springbootupbasicjpql.controller;

import com.example.springbootupbasicjpql.dto.OrdersCustomerDto;
import com.example.springbootupbasicjpql.dto.OrdersCustomerProductDto;
import com.example.springbootupbasicjpql.dto.OrdersDto;
import com.example.springbootupbasicjpql.entity.Orders;
import com.example.springbootupbasicjpql.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    // Orders 조회하는 데 연관된 Customer과 Product 필요 없음


    private final OrdersService ordersService;

    // #1 N + 1 문제 발생 + 예외 (Jseon 순환 참조) => Lazy Loading 필요
    @GetMapping("/listorders1")
    public List<Orders> listOrders1(@RequestParam("gender") char gender){
        System.out.println("listOrders1 ________________________________");
        List<Orders> ordersList = ordersService.listOrders1(gender);
        System.out.println("listOrders1 ________________________________");
        return ordersList;
    }


    //  #2  Lazy Loading으로 N + 1 문제 해결 , 그러나 예외 (Json 순환 참조) 발생
    @GetMapping("/listorders2")
    public List<Orders> listOrders2(@RequestParam("gender") char gender){
        System.out.println("listOrders1 ________________________________");
        List<Orders> ordersList = ordersService.listOrders2(gender);
        System.out.println("listOrders1 ________________________________");
        return ordersList;

        //연관관계를 가진 entity를 조회할 때는 json으로 응답 처리하지 않고 dto를 활용하자
    }

    //  #3  Lazy Loading으로 N + 1 문제 해결 , Dto 사용으로 예외 (Json 순환 참조) 해결
    @GetMapping("/listorders3")
    public List<OrdersDto> listOrders3(@RequestParam("gender") char gender){
        System.out.println("listOrders1 ________________________________");
        List<OrdersDto> ordersList = ordersService.listOrders3(gender);
        System.out.println("listOrders1 ________________________________");
        return ordersList;

        //연관관례를 가진 entity를 조회할 때는 json으로 응답 처리하지 않고 dto를 활용하자
    }

    //  #4  Lazy Loading, Dto 사용 , CustomerDto 전달
    // inner join + lazy 조건 =>  orders 조회 1건 + customer 조회 2건 발생
    // inner join fetch + LAZY 조건 => orders 조회 1건
    @GetMapping("/listorders4")
    public List<OrdersDto> listOrders4(@RequestParam("gender") char gender){
        System.out.println("listOrders4 ________________________________");
        List<OrdersDto> ordersList = ordersService.listOrders4(gender);
        System.out.println("listOrders4 ________________________________");
        return ordersList;

        //연관관례를 가진 entity를 조회할 때는 json으로 응답 처리하지 않고 dto를 활용하자
    }

    //  #5 Lazy Loading, Dto 사용 , CustomerDto 전달
    // Orders 조회하는데 연간된 Customer 또는 product 전달
    @GetMapping("/listordersCustomer1")
    public List<OrdersCustomerDto> listordersCustomer1(){
        System.out.println("listOrders5 ________________________________");
        List<OrdersCustomerDto> ordersList = ordersService.listOrdersCustomer1();
        System.out.println("listOrders5 ________________________________");
        return ordersList;

    }

    //  #6

    @GetMapping("/listorders1Dto")
    public List<OrdersDto> listOrders1Dto(@RequestParam("gender") char gender){
        System.out.println("listorders1Dto ________________________________");
        List<OrdersDto> ordersList = ordersService.listOrders1Dto(gender);
        System.out.println("listorders1Dto ________________________________");
        return ordersList;
    }


    @GetMapping("/listOrdersCustomerProduct")
    public List<OrdersCustomerProductDto> listOrdersCustomerProduct(@RequestParam("gender") char gender,@RequestParam("price") int price){
        System.out.println("listorders1Dto ________________________________");
        List<OrdersCustomerProductDto> ordersList = ordersService.listOrdersCustomerProduct(gender,price);
        System.out.println("listorders1Dto ________________________________");
        return ordersList;
    }
}
