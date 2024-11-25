package com.santosh.booknestapp.dao;

import com.santosh.booknestapp.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    List<Product> findByName(String name);
    List<Product> findByBestSellerTrueOrderById(Pageable pageable);
    List<Product> findByNameContainingIgnoreCase(String name);

//    @Query(value = "SELECT * FROM public.product WHERE id IN :ids", nativeQuery = true)
    List<Product> findByIdIn(List<Integer>ids);//got rid of query by having Data JPA Method which makes our work ease
}
