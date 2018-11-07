package com.wzs.shirospring.mapper;

import com.wzs.shirospring.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 根据用户名查询该用户
     * @param username
     * @return
     */
    User queryUserByUsername(@Param("username") String username);

}
