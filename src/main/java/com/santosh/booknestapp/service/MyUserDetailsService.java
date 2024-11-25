package com.santosh.booknestapp.service;

import com.santosh.booknestapp.dao.UserDao;
import com.santosh.booknestapp.model.User;
import com.santosh.booknestapp.util.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserDao userDao;
    public  MyUserDetailsService(UserDao userDao){
        this.userDao=userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.findByEmail(username);
        UserDetails userDetails=new UserDetailsImpl(user);
        return userDetails;
    }
}
