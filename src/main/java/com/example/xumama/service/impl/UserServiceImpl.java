package com.example.xumama.service.impl;

import com.example.xumama.entity.User;
import com.example.xumama.mapper.UserMapper;
import com.example.xumama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author zhangshun
 * @date 2022/8/8
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 用户名密码登录
     *
     * @param username 描述
     * @param password 描述
     *
     * @return 返回结果
     * @author zhangShun 2022/8/8
     */
    @Override
    public boolean login(String username, String password) {
        User user = userMapper.selectByPrimaryKey(username);
        if(user != null){
            return user.getPassword().equals(password);
        }
        return false;
    }
}
