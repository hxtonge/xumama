package com.example.xumama.service;

import com.example.xumama.entity.User;

/**
 * UserService
 *
 * @author zhangshun
 * @date 2022/8/8
 */
public interface UserService {

    /**
     * 用户名密码登录
     * @param username 描述
     * @param password 描述
     * @return 返回结果
     * @author zhangShun 2022/8/8
     */
    boolean login(String username, String password);

    User getUser(String username);

    void updateMyPassword(Object loginId,String password);
}
