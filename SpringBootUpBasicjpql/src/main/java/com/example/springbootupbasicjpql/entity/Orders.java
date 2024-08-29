package com.example.springbootupbasicjpql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;
    @Column(name = "order_quantity")
    private int orderQuantity;
    private LocalDate orderDate;


}
