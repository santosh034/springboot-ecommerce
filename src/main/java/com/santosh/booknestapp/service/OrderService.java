package com.santosh.booknestapp.service;

import com.santosh.booknestapp.controller.OrderController;
import com.santosh.booknestapp.dao.OrderDao;
import com.santosh.booknestapp.model.Order;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    Logger logger= LoggerFactory.getLogger(OrderService.class);
    private final OrderDao orderDao;
    public OrderService(OrderDao orderDao){
        this.orderDao=orderDao;
    }
    public List<Order> getUserOrders(Integer userId) {
        try{
            return  orderDao.findByUserId(userId);
        }catch (Exception e){
            logger.error("Failed to get User Orders of the userId: "+userId, e.getMessage());
            return  List.of();
        }
    }

    public Order createOrder(Order order) {
        try {
            return orderDao.save(order);
        }catch (Exception e){
            logger.error("Failed to create order: "+order.getId(),e.getMessage());
            return null;
        }
    }
}
