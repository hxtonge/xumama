package com.example.xumama.mapper;

import com.example.xumama.entity.Tangshui;
import org.springframework.stereotype.Repository;

/**
 *  TangshuiMapper
 *
 *  @author zhangshun
 *  @date 2022/8/8
 */
@Repository
public interface TangshuiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tangshui record);

    int insertSelective(Tangshui record);

    Tangshui selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tangshui record);

    int updateByPrimaryKey(Tangshui record);
}