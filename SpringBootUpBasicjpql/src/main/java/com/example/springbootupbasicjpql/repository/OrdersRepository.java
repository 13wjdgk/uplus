package com.example.springbootupbasicjpql.repository;

import com.example.springbootupbasicjpql.dto.OrdersCustomerDto;
import com.example.springbootupbasicjpql.dto.OrdersCustomerProductDto;
import com.example.springbootupbasicjpql.dto.OrdersDto;
import com.example.springbootupbasicjpql.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    // #1 여성고객이 주문한 주문 전체
    @Query("SELECT o FROM Orders o inner join o.customer c where c.gender = :gender")
    List<Orders> listOrders1(@Param("gender") char gender);

    // #4 여성고객이 주문한 주문 전체 + customer도 사용
    // inner join + lazy 조건 =>  orders 조회 1건 + customer 조회 2건 발생
    // inner join fetch + LAZY 조건 => orders 조회 1건
    @Query("SELECT o FROM Orders o inner join fetch o.customer c where c.gender = :gender")
    List<Orders> listOrders4(@Param("gender") char gender);


    // #5 여성고객이 주문한 주문 전체 + customer도 사용 , 필요한 일부만 조회
    // fetch join은 Entity 전체를 사용
    @Query("""
            SELECT new com.example.springbootupbasicjpql.dto.OrdersCustomerDto(
             o.id as orderId , c.name as customerName ,c.phone as customerPhone ,o.orderDate as orderDate
             )
            
            FROM Orders o inner join o.customer c 
    """)
    List<OrdersCustomerDto> listOrdersCustomer1();


    // #6 #1의 DTO 버전
    // fetch join은 Entity 전체를 사용
    @Query("SELECT new com.example.springbootupbasicjpql.dto.OrdersDto(o.id,o.orderQuantity,o.orderDate) FROM Orders o inner join o.customer c where c.gender = :gender")
    List<OrdersDto> listOrders1Dto(@Param("gender") char gender);


    @Query("""
            select new com.example.springbootupbasicjpql.dto.OrdersCustomerProductDto( 
                    o.id, 
                    c.name , c.phone , 
                    p.name , p.price , p.country , o.orderDate 
            )  
              from Orders o inner join o.customer c
                                  join o.product p
             where c.gender = :gender
               and p.price >= :price
    """)
    List<OrdersCustomerProductDto> listOrdersCustomerProduct(@Param("gender") char gender, @Param("price") int price);
}
