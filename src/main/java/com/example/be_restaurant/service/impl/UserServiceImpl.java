package com.example.be_restaurant.service.impl;

import com.example.be_restaurant.bean.response.UserResponse;
import com.example.be_restaurant.exception.NotFoundException;
import com.example.be_restaurant.mapper.UserMapper;
import com.example.be_restaurant.repository.UserRepository;
import com.example.be_restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse getUser(String username) {
        return UserMapper.convertToUserResponse(userRepository.findByUsernameAndStatus(username, true)
                .orElseThrow(() -> new NotFoundException("username", "khong tim thay")));
    }
}
