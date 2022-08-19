package com.example.xumama.mapper;

import com.example.xumama.entity.User;
import org.springframework.stereotype.Repository;

/**
 *  UserMapper
 *
 *  @author zhangshun
 *  @date 2022/8/8
 */
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    void updateByPrimaryKey(User record);
}