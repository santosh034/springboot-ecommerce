package com.santosh.booknestapp.service;

import com.santosh.booknestapp.dao.ProductDao;
import com.santosh.booknestapp.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductService {
    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(ProductService.class);

    private final ProductDao productDao;

    public  ProductService(ProductDao productDao){
        this.productDao=productDao;
    }

    public String loadProducts(List<Product> products){
        try{
            productDao.saveAll(products);
            return "success";
        }catch (Exception e){
            logger.error("Error loading products", e.getMessage(), e);
            return "error";
        }
    }
    public List<Product> getAllProducts(String name){
        try{
            if(name==null||name.trim().isEmpty()){
                return productDao.findAll();
            }else {
                return productDao.findByNameContainingIgnoreCase(name);
            }
        }catch (Exception e){
            logger.error("Error retrieving products", e.getMessage(), e);
            return List.of();
        }
    }

    public List<Product> getFeaturedProducts(){
        try{
            List<Integer> ids= Arrays.asList(95, 98, 92);
            return productDao.findByIdIn(ids);
        }catch (Exception e){
            logger.error("Error retrieving featured products", e.getMessage(), e);
            return List.of();
        }
    }

    public Product getProductById(Integer id){
        try{
            return productDao.findById(id).get();
        }catch (Exception e){
            logger.error("Error retrieving product detail: "+id, e.getMessage(), e);
            return null;
        }
    }
}
