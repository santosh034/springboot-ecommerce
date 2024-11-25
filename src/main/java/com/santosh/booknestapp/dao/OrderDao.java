package com.santosh.booknestapp.dao;

import com.santosh.booknestapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
    public List<Order> findByUserId(Integer userId);
}
