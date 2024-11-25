package com.santosh.booknestapp.dao;

import com.santosh.booknestapp.model.Cart;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
    public Cart findByUserId(Integer userId);
}
