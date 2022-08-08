package com.example.xumama.mapper;

import com.example.xumama.entity.Caidan;
import org.springframework.stereotype.Repository;

/**
 *  CaidanMapper
 *
 *  @author zhangshun
 *  @date 2022/8/8
 */
@Repository
public interface CaidanMapper {
    int insert(Caidan record);

    int insertSelective(Caidan record);

    Caidan selectCaidanToday();
}