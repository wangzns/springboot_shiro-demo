package com.wzs.shirospring.service;

import com.wzs.shirospring.model.User;

import java.util.List;

public interface UserService {

    User findUserByUsername(String username);
}
