package com.wzs.shirospring.service.impl;

import com.wzs.shirospring.mapper.UserMapper;
import com.wzs.shirospring.model.User;
import com.wzs.shirospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }
}
