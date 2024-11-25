package com.santosh.booknestapp.service;

import com.santosh.booknestapp.dao.UserDao;
import com.santosh.booknestapp.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;
    org.slf4j.Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String register(User user) {
        try {
            user.setName(user.getName());
            user.setEmail(user.getEmail());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
            return "success";
        } catch (Exception e) {
            logger.error("Error registering user: " + user.getName(), e.getMessage(), e);
            return "error";
        }
    }

    public User findUserByEmail(String email){
        try {
            return userDao.findByEmail(email);
        } catch (Exception e) {
            logger.error("Error getting user Details : " + email, e.getMessage(), e);
            return null;
        }
    }
    public User login(User user) {
        try {
            return userDao.findByEmail(user.getEmail());
        } catch (Exception e) {
            logger.error("Failed to find user by email: " + user.getEmail(), e.getMessage(), e);
            return null;
        }
    }

    public User findUserById(Integer id) {
        try {
            return userDao.findById(id).get();
        } catch (Exception e) {
            logger.error("Failed to find user by id: " + id, e.getMessage(), e);
            return null;
        }
    }
}
