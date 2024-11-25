package com.santosh.booknestapp.controller;

import com.santosh.booknestapp.model.Product;
import com.santosh.booknestapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public  ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/products/load")
    public ResponseEntity<String> loadProducts(@RequestBody List<Product> products) {
        String result = productService.loadProducts(products);
        if ("success".equals(result)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Products loaded successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to load products");
        }
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String name) {
        List<Product> products = productService.getAllProducts(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product==null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("/featuredProducts")
    public ResponseEntity<List<Product>> getFeaturedProducts() {
        List<Product> products = productService.getFeaturedProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }
}
