package com.santosh.booknestapp.service;

import com.santosh.booknestapp.dao.CartDao;
import com.santosh.booknestapp.model.Cart;
import com.santosh.booknestapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    Logger logger=LoggerFactory.getLogger(CartService.class);

    private final CartDao cartDao;

    public CartService(CartDao cartDao){
        this.cartDao=cartDao;
    }
    public List<Cart> getUserCart(Integer userId) {
        try{
            Cart cart=cartDao.findByUserId(userId);
            if(cart==null) {
                return List.of();
            }
            return List.of(cart);
        }catch (Exception e){
            logger.error("Failed to get User Cart List: "+userId,e.getMessage());
            return List.of();
        }
    }

    public String saveUserCart(Cart cart) {
        try{
            User user = cart.getUser();
            Cart existingCartOpt = cartDao.findByUserId(user.getId());

            if (existingCartOpt!=null) {
                existingCartOpt.setCartList(cart.getCartList());
                existingCartOpt.setTotalAmount(cart.getTotalAmount());
                existingCartOpt.setQuantity(cart.getQuantity());
                cartDao.save(existingCartOpt);
            } else {
                cartDao.save(cart);
            }
            return "success";
        }catch (Exception e){
            logger.error("Error saving cart for the user: "+cart.getUser().getName(),e.getMessage());
            return "error";
        }
    }
}
