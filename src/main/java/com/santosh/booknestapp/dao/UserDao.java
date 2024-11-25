package com.santosh.booknestapp.dao;


import com.santosh.booknestapp.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByName(String userName);
    User findByEmail(String email);
}
