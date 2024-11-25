package com.santosh.booknestapp.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToMany
    private List<Product> cartList;

    private BigDecimal amountPaid;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
