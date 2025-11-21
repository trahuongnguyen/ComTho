package com.example.be_restaurant.service;

import com.example.be_restaurant.bean.response.UserResponse;
import com.example.be_restaurant.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponse getUser(String username);
}
