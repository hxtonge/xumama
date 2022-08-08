package com.example.xumama.entity;

import lombok.Data;

/**
 *  User
 *
 *  @author zhangshun
 *  @date 2022/8/8
 */
@Data
public class User {
    private String id;

    private String name;

    private String password;

    private String ip;

    private String isAdmin;
}