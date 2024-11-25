package com.santosh.booknestapp.controller;

import com.santosh.booknestapp.model.Cart;
import com.santosh.booknestapp.service.CartService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService){
        this.cartService=cartService;
    }

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getUserCart(@RequestParam Integer userId){
        List<Cart> cart= cartService.getUserCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/carts")
    public ResponseEntity<String> saveOrUpdateUserCart(@RequestBody Cart cart){
        String cartStatus=cartService.saveUserCart(cart);
        if("success".equals(cartStatus)){
            return ResponseEntity.ok("User Cart Saved Successfully");
        }else {
            return ResponseEntity.noContent().build();
        }
    }
}
