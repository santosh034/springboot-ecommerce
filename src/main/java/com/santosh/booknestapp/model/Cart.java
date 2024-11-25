package com.santosh.booknestapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToMany
    private List<Product> cartList;

    private BigDecimal totalAmount;

    private Integer quantity;

    @OneToOne
    private User user;
}

