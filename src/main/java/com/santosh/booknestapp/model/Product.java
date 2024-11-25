package com.santosh.booknestapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(length = 1000)
    private String overview;

    @JsonProperty("long_description")
    @Column(length = 4000)
    private String longDescription;

    private BigDecimal price;

    private String poster;

    private int rating;

    @JsonProperty("in_stock")
    private boolean inStock;

    private int size;

    @JsonProperty("best_seller")
    private boolean bestSeller;
}
